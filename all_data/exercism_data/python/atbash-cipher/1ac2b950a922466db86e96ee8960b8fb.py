import string
delchars = ''.join(c for c in map(chr, range(256)) if not c.isalnum())
cipher = str.maketrans(string.ascii_lowercase, string.ascii_lowercase[::-1], delchars)

def encode(string):
    string = string.lower().translate(cipher)
    chunks = [string[i:i+5] for i in range(0, len(string)-5, 5)]
    final = len(string)%5 if len(string)%5 != 0 else 5
    chunks.append(string[-final:])
    return ' '.join(chunks)
         
def decode(string):
    return string.replace(' ', '').lower().translate(cipher)


