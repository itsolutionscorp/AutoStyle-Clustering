#Improved script by using python's string library   
import re, string

transTable = str.maketrans(string.ascii_lowercase, string.ascii_lowercase[::-1])

def encode(plaintext):
    plaintext = re.sub('[%s]' % re.escape(string.punctuation + string.whitespace), '', plaintext)
    plaintext = plaintext.lower().translate(transTable)
    plaintext = ' '.join([plaintext[i:i+5] for i in range(0,len(plaintext), 5)])
    return plaintext
    
def decode(encoded):
    encoded = re.sub('[%s]' % re.escape(string.whitespace), '', encoded)
    return encoded.translate(transTable)
