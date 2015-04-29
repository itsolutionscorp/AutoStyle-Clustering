from string import maketrans, ascii_lowercase, punctuation

def atbash(text):
    atbash = maketrans(ascii_lowercase, ascii_lowercase[::-1])
    return text.translate(atbash, punctuation)

def encode(plaintext):
    text = plaintext.lower().replace(' ','')
    text = atbash(text)
    words = [text[n:n+5] for n in range(0, len(text)-1, 5)]
    return ' '.join(words)

def decode(ciphertext):
    text = atbash(ciphertext)
    return text.replace(' ','')
