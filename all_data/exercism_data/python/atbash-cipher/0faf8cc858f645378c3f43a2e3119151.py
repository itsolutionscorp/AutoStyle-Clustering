CIPHER_KEY = {
    'a': 'z',
    'b': 'y',
    'c': 'x',
    'd': 'w',
    'e': 'v',
    'f': 'u',
    'g': 't',
    'h': 's',
    'i': 'r',
    'j': 'q',
    'k': 'p',
    'l': 'o',
    'm': 'n',
    'n': 'm',
    'o': 'l',
    'p': 'k',
    'q': 'j',
    'r': 'i',
    's': 'h',
    't': 'g',
    'u': 'f',
    'v': 'e',
    'w': 'd',
    'x': 'c',
    'y': 'b',
    'z': 'a',
    '0': '0',
    '1': '1',
    '2': '2',
    '3': '3',
    '4': '4',
    '5': '5',
    '6': '6',
    '7': '7',
    '8': '8',
    '9': '9'
}

def encode(word):
    word = ''.join([char for char in list(word) if char.isalpha() or char.isdigit()])
    output = ''
    for i, char in enumerate(word):
        if (i+1) % 5 != 0 or i == len(word)-1:
            output += CIPHER_KEY[char.lower()]
        else:
            output += CIPHER_KEY[char.lower()] + ' '
    return output

def decode(word):
    return ''.join([CIPHER_KEY[char.lower()] for char in list(word) if char != ' '])
