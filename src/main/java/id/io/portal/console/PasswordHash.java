package id.io.portal.console;

import id.io.portal.manager.EncryptionManager;

public class PasswordHash {

    public static void main(String[] args) {

        EncryptionManager.init();
        System.out.println(EncryptionManager.encrypt("Secret123!"));
        System.out.println(EncryptionManager.decrypt("8y8oEYyYFz3kPbwexsEC9g=="));
        EncryptionManager.shutdown();
    }
        
}
