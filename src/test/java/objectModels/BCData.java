package objectModels;

import java.io.File;

/**
 * Created by Любовь on 28.11.2017.
 */
public class BCData {
    public String qiwiWallet;
    public String email;
    public String bcWallet;

    public BCData(String qiwiWallet, String email, String bcWallet) {
        this.qiwiWallet = qiwiWallet;
        this.email = email;
        this.bcWallet = bcWallet;
    }

    public String getQiwiWallet() {
        return qiwiWallet;
    }

    public String getEmail() {
        return email;
    }

    public String getBCWallet() {
        return bcWallet;
    }
}
