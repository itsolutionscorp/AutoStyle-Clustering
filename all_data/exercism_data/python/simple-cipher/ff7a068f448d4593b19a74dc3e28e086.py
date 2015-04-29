import re
import random

class Caesar():
    def encode(self, text, key='d'*100):
        ret = ""
        i = 0
        for x in re.sub('[^a-z]','',text.lower()):
            tmp = ord(key[i]) - ord('a')
            if ord(x) + tmp > 122:
                ret += chr(ord(x) + tmp - 26)
            else:
                ret += chr(ord(x) + tmp)
            i += 1
        return ret

    def decode(self, text, key='d'*100):
        ret = ""
        i = 0
        for x in re.sub('[^a-z]','',text.lower()):
            tmp = ord(key[i]) - ord('a')
            if ord(x) - tmp < 97:
                ret += chr(ord(x) - tmp + 26)
            else:
                ret += chr(ord(x) - tmp)
            i += 1
        return ret


class Cipher():

    def __init__(self, key=''):
        self.flg = False
        if key == '':
            self.key = self.createKey()
        else:
            if re.match('[A-Z|0-9]', key):
                raise ValueError
            if len(set(key)) == 1:
                self.key = list(set(key))[0]*100
            else:
                self.key = key

    def encode(self, text):
        return Caesar().encode(text, self.key)

    def decode(self, text):
        return Caesar().decode(text, self.key)

    def createKey(self):
        ret = ''
        for i in range(100):
            ret += chr(random.randint(ord('a'), ord('z')))
        return ret
