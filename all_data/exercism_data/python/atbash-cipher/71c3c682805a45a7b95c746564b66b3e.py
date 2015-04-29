from string import punctuation

forwards = 'abcdefghijklmnopqrstuvwxyz'
backwards = 'zyxwvutsrqponmlkjihgfedcba'

encoding = {f : b for f,b in zip(forwards,backwards)}
decoding = {b : f for f,b in zip(forwards,backwards)}

size = 5

def encode(decrypted):
    decrypted = decrypted.lower()
    decrypted = decrypted.replace(' ', '')
    encrypted = ''.join([encoding.get(c,c) for c in decrypted
                    if c not in punctuation])

    blobs = len(encrypted) / size + 1
    encrypted = ' '.join([encrypted[size*i:size*(i+1)] for i in range(blobs)])

    return encrypted.strip()

def decode(encrypted):
    encrypted = encrypted.replace(' ', '')
    decrypted = ''.join([decoding.get(c, c) for c in encrypted])
    return decrypted.lower()
