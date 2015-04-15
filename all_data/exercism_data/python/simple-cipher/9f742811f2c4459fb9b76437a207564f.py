__author__ = 'tracyrohlin'

import string, random


class Cipher:
    def __init__(self, key=None):
        if key:
            self.key = key
        else:
            self.key = self.generate_key()



    def generate_key(self):
        return ''.join(random.choice(string.ascii_lowercase) for n in range(100))



    def encode(self, text):
        new_sentence = "".join(char for char in text if char.isalpha())
        text = list(new_sentence.lower())
        new_list = []

        for i in range(len(text)):
            if i < len(self.key):
                cipher_num = ord(self.key[i])-ord("a")
                if ord(text[i]) + cipher_num < 123:
                    new_list.append(chr(ord(text[i])+cipher_num))
                else:
                    new_list.append(chr(ord(text[i])+cipher_num-26))
            else:
                break
        new_list.extend(list(text[len(self.key)::]))
        message = "".join(new_list)
        return message

    def decode(self, text):
        new_sentence = "".join(char for char in text if char.isalpha())
        text = list(new_sentence.lower())
        new_list = []

        for i in range(len(text)):
            if i < len(self.key):
                cipher_num = ord("a") - (ord(self.key[i]))
                if ord(text[i]) + cipher_num < 97:
                    new_list.append(chr(ord(text[i]) + cipher_num+26))
                else:
                    new_list.append(chr(ord(text[i])+cipher_num))
            else:
                break
        new_list.extend(list(text[len(self.key)::]))
        message = "".join(new_list)
        return message


class Caesar(Cipher):
    def generate_key(self):
        return "d" * 100
