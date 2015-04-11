"""
"""
import string
alpha = 'abcdefghijklmnopqrstuvwxyz'
atbash = {}
count = 0
for ch in alpha:
    count += 1
    atbash[ch] = alpha[-count]

def encode(p):
    p = p.replace(' ','').lower()
    for char in string.punctuation:
        p = p.replace(char, '')
    ciphertext = ''
    count = 0
    for char in p:
        count += 1
        if char not in alpha:
            ciphertext += char
        else:
            ciphertext += atbash[char]
        if count % 5 == 0:
            ciphertext += ' '
    return ciphertext.strip()
    
def decode(c):
    c = c.replace(' ','')
    plaintext = ''
    for char in c:
        if char not in alpha:
            plaintext += char
        else:
            plaintext += atbash[char]

    return plaintext
