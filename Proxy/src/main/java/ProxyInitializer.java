import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProxyInitializer extends ChannelInitializer<SocketChannel> {

    private final ThemeProxy themeProxy;
    private final List<Integer> ports;

    public ProxyInitializer(ThemeProxy themeProxy) {

        this.themeProxy = themeProxy;
        ports = new ArrayList<>();

        try {
            getPortsFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void getPortsFromFile() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("ports.txt"));

        while (scanner.hasNext()) {
            ports.add(scanner.nextInt());
        }

        scanner.close();

    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        Bootstrap bootstrap = new Bootstrap();
        Channel channel = bootstrap.group(themeProxy.getWorkerGroup())
                .channel(NioSocketChannel.class)
                .handler(new ServerInitializer(socketChannel))
                .connect("localhost", roundRobinMethod()).sync().channel();

        socketChannel.pipeline()
                .addLast(new StringDecoder())
                .addLast(new StringEncoder())
                .addLast(new SimpleChannelInboundHandler<String>() {

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

                        channel.writeAndFlush(msg);

                        System.out.printf("Current server: %d\n", portIndex);

                    }

                });

    }

    private int portIndex = 0;

    private int roundRobinMethod() {

        int port = ports.get(portIndex++);
        System.out.println(port);

        if (portIndex >= ports.size()) {
            portIndex = 0;
        }

        return port;

    }

}
