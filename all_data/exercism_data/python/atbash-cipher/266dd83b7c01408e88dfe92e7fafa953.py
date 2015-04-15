PLAIN = "abcdefghijklmnopqrstuvwxyz"
CIPHER = "zyxwvutsrqponmlkjihgfedcba"

def decode(ciphertext):
    d = str.maketrans(CIPHER, PLAIN)
    clean = "".join(ciphertext.split())
    return clean.translate(d)

def encode(plaintext):
    d = str.maketrans(PLAIN, CIPHER)
    clean = "".join([c.lower() for c in plaintext if c.isalnum()])
    encoded = clean.translate(d)
    return ' '.join(encoded[i:i+5] for i in range(0,len(encoded),5))
