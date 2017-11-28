package testuniverse.easyqa.tests;

import java.io.File;

/**
 * Created by Любовь on 28.11.2017.
 */
public class BCData {
    private final String qiwiWallet;
    private final String email;
    private final String bcWallet;

    public File getInputData() {
        return inputData;
    }

    public void setInputData(File inputData) {
        this.inputData = inputData;
    }

    private File inputData;

    public File getOutputData() {
        return outputData;
    }

    public void setOutputData(File outputData) {
        this.outputData = outputData;
    }

    private File outputData;

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
