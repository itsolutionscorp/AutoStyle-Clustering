from string import maketrans

class Cipher(object):
    def __init__(self, cipher_key = 'duxrceqyaimciuucnelkeoxjhdyduucpmrxmaivacmybmsdrzwqxvbxsygzsabdjmdjabeorttiwinfrpmpogvabiofqexnohrqu'):
        self.letter_list = list('abcdefghijklmnopqrstuvwxyz')
        self.cipher_key = list(cipher_key)
        
    def __call__(self):
        return self

    def code(self, text, transtype):
        text = ''.join([i for i in text.lower() if i.isalpha()])
        result = []
        cklen = len(self.cipher_key)
        x = 0
        y = 0
        for t in range(len(text)):
            for l in range(len(self.letter_list)):
                if self.cipher_key[t % len(self.cipher_key)] == self.letter_list[l]:
                    x = l
                if text[t] == self.letter_list[l]:
                    y = l
            if transtype == 'en':        
                if x + y < 26:
                    result.append(self.letter_list[x + y])
                else:
                    result.append(self.letter_list[x + y - 26])
            if transtype == 'de':
                if y - x >= 0:
                    result.append(self.letter_list[y - x])
                else:
                    result.append(self.letter_list[y - x + 26])
        return ''.join(result)

    def decode(self, text):
        return self.code(text, 'de')

    def encode(self, text):
        return self.code(text, 'en')
            

Caesar = Cipher('dddddd')
