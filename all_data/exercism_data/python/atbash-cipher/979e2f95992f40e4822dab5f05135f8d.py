import string

PLAIN_ALPHABET = string.ascii_lowercase
CIPHER_ALPHABET = PLAIN_ALPHABET[::-1]
TRANSLATION_TABLE = string.maketrans(PLAIN_ALPHABET,CIPHER_ALPHABET)

def decode(message):
    if not isinstance(message,basestring):
        return None
    return message.lower().translate(TRANSLATION_TABLE,string.punctuation+string.whitespace)

def encode(message):
    if not isinstance(message,basestring):
        return None
    message = message.lower().translate(TRANSLATION_TABLE,string.punctuation+string.whitespace)
    return ' '.join([message[i:i+5] for i in range(0,len(message),5) ])
