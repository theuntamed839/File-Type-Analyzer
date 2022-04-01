package structure;

import java.util.List;

public class Structure{
    private String fileType;
    private String mime;
    private List<Signature> signature;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public List<Signature> getSignature() {
        return signature;
    }

    public void setSignature(List<Signature> signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "Structure{" +
                "fileType='" + fileType + '\'' +
                ", mime='" + mime + '\'' +
                ", signature=" + signature +
                '}';
    }
}