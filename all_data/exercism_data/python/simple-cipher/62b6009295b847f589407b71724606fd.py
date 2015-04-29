class Caesar(object):

    def __init__(self,):

        self.keys = {}

        for i in xrange(26):
            
            forward = 97 + i + 3
            if forward > 122: forward -= 26
            
            backward = 97 + i - 3
            if backward < 97: backward += 26
            
            self.keys[chr(97+i)] = {'forward':chr(forward),
                                    'backward':chr(backward)}

    def encode(self, s):

        return ''.join([self.keys[letter.lower()]['forward'] for letter in s if letter.isalpha()])

    def decode(self, s):

        return ''.join([self.keys[letter.lower()]['backward'] for letter in s if letter.isalpha()])

class Cipher(object):

    def __init__(self,cipher = ''):
        self.cipher = []
        
        if cipher == '':
            from random import randint
            cipher = ''.join([chr(randint(97,122)) for i in xrange(26)])
            
        self.key = cipher

        for letter in cipher:
            
            value = ord(letter) - 97
            keys = {}

            for i in xrange(26):
                
                forward = 97 + i + value
                if forward > 122: forward -= 26
                
                backward = 97 + i - value
                if backward < 97: backward += 26
                
                keys[chr(97+i)] = {'forward':chr(forward),
                                   'backward':chr(backward)}

            self.cipher.append(keys)

    def encode(self,s):
        return ''.join([(self.cipher[i][letter.lower()]['forward'] if i < len(self.cipher) else letter.lower()) for i, letter in enumerate(s) if letter.isalpha()])

    def decode(self,s):
        return ''.join([(self.cipher[i][letter.lower()]['backward'] if i < len(self.cipher) else letter.lower()) for i, letter in enumerate(s) if letter.isalpha()])
