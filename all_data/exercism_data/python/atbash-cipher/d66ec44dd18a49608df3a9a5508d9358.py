import string

def cipher():
    plain = "abcdefghijklmnopqrstuvwxyz0123456789"
    cipher = "zyxwvutsrqponmlkjihgfedcba0123456789"
    return string.maketrans(plain, cipher)

def code(text):
    return text.lower().translate(cipher(), string.punctuation + string.whitespace)

def chunk(text):
    return "".join([ text[ch:ch+5] + ' ' for ch in range(0,len(text),5)]).rstrip()

def encode(text):
    return chunk(code(text))

def decode(text):
    return code(text)
