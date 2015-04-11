import string
import operator
class Cipher:
    
    chars = string.ascii_lowercase                        
                                  
    def __init__(self,key='d'):
        ##since one char can correspond to a double digit
        ##this fixes it
        digits = [str(i) for i in range(len(string.ascii_lowercase))]
        dist = str.maketrans(dict(zip(string.ascii_lowercase,digits)))
        self.key=" ".join(([i for i in key])).translate(dist).split()
        
    
    def encode(self,what):
        return self.i_help(what.lower(),0,operator.gt,operator.add,operator.sub)

    def decode(self,what):
        return self.i_help(what.lower(),0,operator.lt,operator.sub,operator.add)
        
    def i_help(self,what,limit,op1,op2,op3):
        ##the differences between encode and decode:
        ##the check for boundaries
        ##add key for encode
        ##sub key for decode
        msg = ''
        ##since zip truncates the shorter object i have to make sure
        ##the key is larger than the input
        if len(set(self.key))==1:
            self.key = [self.key[0] for i in range(len(what))]
        for key,char in zip(self.key,what):
            for cnt,i in enumerate(self.chars):
                if char==i:
                    if op1(cnt+int(key),limit):
                        msg += (self.chars[op3(op2(cnt,int(key)),len(self.chars))])
                    else:
                        
                        msg +=(self.chars[op2(cnt,int(key))])
        return msg
    

class Caesar(Cipher):
    ###inherits everything from Cipher and since the default key in Cipher is the same as the Caesar one this class does pretty much nothing
    pass
