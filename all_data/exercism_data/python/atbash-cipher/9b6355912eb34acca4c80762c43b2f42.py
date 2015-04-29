import string

lower = string.ascii_lowercase
delchars = ''.join(c for c in map(chr, range(256)) if not c.isalnum())
cipher = str.maketrans(lower, lower[::-1], delchars)

def encode(message):
    message = message.lower().translate(cipher)
    return ' '.join([message[i:i+5] for i in range(0, len(message), 5)])
         
def decode(message):
    return message.lower().translate(cipher)
    


