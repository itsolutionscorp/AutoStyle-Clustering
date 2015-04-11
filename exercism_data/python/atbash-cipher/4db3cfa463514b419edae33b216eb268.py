import string

plain = string.ascii_lowercase
cipher = ''.join(reversed(plain))

plain2cipher = str.maketrans(plain, cipher)
cipher2plain = str.maketrans(cipher, plain)

def decode(text):
    return ''.join([
        chunk.translate(cipher2plain)
        for chunk in text.split()
    ])

def encode(text):
    stripped = ''.join([
        x for x in text.lower()
        if x not in string.whitespace
        and x not in string.punctuation
    ])
    trans = stripped.translate(plain2cipher)
    chunks = [trans[i:i+5] for i in range(0, len(trans), 5)]
    return ' '.join(chunks)
