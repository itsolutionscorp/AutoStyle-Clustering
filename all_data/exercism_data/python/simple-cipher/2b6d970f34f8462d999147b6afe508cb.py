import random

class Caesar(object):

    ALPHABET = 'abcdefghijklmnopqrstuvwxyz'

    def encode(self, word):
        word = word.lower()
        code = ''
        for letter in word:
            if letter.isalpha():
                new_index = (Caesar.ALPHABET.index(letter)+3)%26
                new_letter = Caesar.ALPHABET[new_index]
                code += new_letter
        return code

    def decode(self, code):
        word = ''
        for letter in code:
            new_index = (Caesar.ALPHABET.index(letter)+23)%26
            new_letter = Caesar.ALPHABET[new_index]
            word += new_letter
        return word

class Cipher(object):

    ALPHABET = 'abcdefghijklmnopqrstuvwxyz'

    def __init__(self, cipher = None):
        if cipher == None:
            self.cipher = ''
            for i in range(100):
                self.cipher += Cipher.ALPHABET[random.randint(0,25)]
        else:
            self.cipher = cipher

    def encode(self, word):
        word = word.lower()
        code = ''
        for i in range(len(word)):
            if word[i].isalpha():
                shift = Cipher.ALPHABET.index(self.cipher[i%len(self.cipher)])
                new_index = (Cipher.ALPHABET.index(word[i])+shift)%26
                new_letter = Cipher.ALPHABET[new_index]
                code += new_letter
        return code

    def decode(self, code):
        word = ''
        for i in range(len(code)):
            shift = Cipher.ALPHABET.index(self.cipher[i])
            new_index = (Cipher.ALPHABET.index(code[i])+(26 - shift))%26
            new_letter = Cipher.ALPHABET[new_index]
            word += new_letter
        return word
