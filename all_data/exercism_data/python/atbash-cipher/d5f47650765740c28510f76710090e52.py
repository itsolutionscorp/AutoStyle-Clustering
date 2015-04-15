alphabet= 'abcdefghijklmnopqrstuvwxyz'
reverse = alphabet[::-1]

def decode(string):
    """Returns a decoded version of the string"""
    s = string.replace(' ', '')
    return ''.join([alphabet[reverse.index(i)] for i in s])

def encode(string):
    """Returns an encoded version of the string"""
    s = string.replace(',', '').replace('.', '').replace(' ', '').lower()
    encoded = ''
    for i in s:
        if i in alphabet:
            encoded += reverse[alphabet.index(i)]
        else:
            encoded += i
    return ' '.join(encoded[i:i+5] for i in range(0,len(encoded),5))
