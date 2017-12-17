package objectModels;

/**
 * Created by Любовь on 15.12.2017.
 */

public class ProxyData {

    public String proxyAdress;
    public String proxyPort;


    public ProxyData(String proxyAdress, String proxyPort) {
        this.proxyAdress = proxyAdress;
        this.proxyPort = proxyPort;

    }

    public String getProxyAdress() {
    return proxyAdress;
}
    public String getProxyPort() {
    return proxyPort;
    }



}


