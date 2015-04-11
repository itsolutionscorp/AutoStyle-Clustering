import string

def make_cipher():
    cipher = {i:j for i,j in zip(string.lowercase, reversed(string.lowercase))}
    for i in range(10):
        cipher[str(i)] = str(i)
    return cipher

def encode(code):
    encoded = ''
    cipher = make_cipher()
    for s in code.replace(' ','').lower():
        if s in string.punctuation:
            continue
        encoded += cipher[s]
    # add a space after every 5 chars
    div5 = [encoded[i:i+5] for i in range(0,len(encoded)+1, 5)]
    return ' '.join(div5).strip()
        

def decode(phrase):
    decoded = ''
    cipher = make_cipher()
    for s in phrase.replace(' ','').lower():
        decoded += cipher[s]
    return decoded.strip()
