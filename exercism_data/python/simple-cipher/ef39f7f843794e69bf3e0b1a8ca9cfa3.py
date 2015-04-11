class Caesar:
    
    def __init__(self):
        pass
        
    def encode(self,string):
        string = "".join([char for char in string if char.isalpha()]).lower()
        encoded = ""
        for char in string:
            encoded += chr(ord("a")+((ord(char)-ord("a")+3)%26))
        return encoded
        
    def decode(self,string):
        string = "".join([char for char in string if char.isalpha()]).lower()
        decoded = ""
        for char in string:
            decoded += chr(ord("a")+((ord(char)-ord("a")-3)%26))
        return decoded
        

import random
import string
        
class Cipher:

    def __init__(self,key="".join(random.choice(string.ascii_lowercase) for _ in range(100))):
        if any(c.isupper() or c.isdigit() for c in key):
            raise ArgumentError("Key must contain only lowercase characters")
        self.key = key
        
    def encode(self,string):
        encoded = ""
        for i in range(len(string)):
            encoded += chr(ord("a")+((ord(string[i])-ord("a")+(ord(self.key[i%len(self.key)])-ord("a")))%26))
        return encoded
        
    def decode(self,string):
        decoded = ""
        for i in range(len(string)):
            decoded += chr(ord("a")+((ord(string[i])-ord("a")-(ord(self.key[i%len(self.key)])-ord("a")))%26))
        return decoded
