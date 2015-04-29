class Caesar:
    def encode(self, plain):
        plaintext = "abcdefghijklmnopqrstuvwxyz"
        cipertext = plaintext[3:] + plaintext[:4]
        
        cipher = ""
        for char in plain:
            if char.isalpha():
                index = plaintext.index(char.lower())
                cipher += cipertext[index]
        
        return cipher
    
    def decode(self, cipher):
        plaintext = "abcdefghijklmnopqrstuvwxyz"
        cipertext = plaintext[3:] + plaintext[:4]
        
        plain = ""
        for char in cipher:
            if char.isalpha():
                index = cipertext.index(char.lower())
                plain += plaintext[index]
        
        return plain

class Cipher:
    def __init__(self, key="a"):
        self.key = key
    
    def encode(self, plain):
        plaintext = "abcdefghijklmnopqrstuvwxyz"
        
        cipher = ""
        for i in range(len(plain)):
            key_index = i%len(self.key)
            key = plaintext.index(self.key[key_index])
            ciphertext = plaintext[key:] + plaintext[:key+1]
            
            char = plain[i]
            index = plaintext.index(char)
            cipher += ciphertext[index]
        
        return cipher
    
    def decode(self, cipher):
        plaintext = "abcdefghijklmnopqrstuvwxyz"
        
        plain = ""
        for i in range(len(cipher)):
            key_index = i%len(self.key)
            key = plaintext.index(self.key[key_index])
            ciphertext = plaintext[key:] + plaintext[:key+1]
            
            char = cipher[i]
            index = ciphertext.index(char)
            plain += plaintext[index]
        
        return plain
