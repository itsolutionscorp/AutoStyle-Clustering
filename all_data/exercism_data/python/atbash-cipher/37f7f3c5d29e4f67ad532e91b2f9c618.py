import re

alphabet = 'abcdefghijklmnopqrstuvwxyz'
numbers = '0123456789'
codec = str.maketrans(alphabet+numbers, ''.join(reversed(alphabet))+numbers)

def slices(source, size=5):
    return [source[i:i+size] for i in range(0, len(source), size)]

def alpha_numeric(source):
    return re.sub('[^a-z0-9]', '', source)

def encode(plain):
    return ' '.join(word.translate(codec) 
                    for word in slices(alpha_numeric(plain.lower())))

def decode(cipher):
    return ''.join(alpha_numeric(cipher.lower()).translate(codec))
