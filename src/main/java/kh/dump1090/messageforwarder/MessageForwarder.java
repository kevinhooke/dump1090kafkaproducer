package kh.dump1090.messageforwarder;

import kh.dump1090.SBSMessage;

import java.io.IOException;

public interface MessageForwarder {

    void forwardMessage(SBSMessage msg) throws IOException;

}
