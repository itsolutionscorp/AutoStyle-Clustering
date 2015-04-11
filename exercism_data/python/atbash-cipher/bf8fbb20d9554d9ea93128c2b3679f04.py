'''
atbash_CIPHER.py

The Atbash CIPHER
'''

ALPHABET = [chr(c) for c in range(ord('a'), ord('z')+1)]
CIPHER = dict(zip(ALPHABET, reversed(ALPHABET)))
DIGITS = map(str, range(10))
CIPHER.update(dict(zip(DIGITS, DIGITS)))

def _transform(message):
    '''
    Transform function
    @param message: the message to transform
    @returns: list of characters representing the tranformed string
    '''
    return [CIPHER[c.lower()] for c in message if c.lower() in CIPHER]

def decode(message):
    '''
    Decode the given message
    @param message: the message to decode
    @returns: the decoded message
    '''
    decoded = _transform(message)
    return ''.join(decoded)

def encode(message):
    '''
    Encode the given message
    @param message: the message to decode
    @returns: the encoded message
    '''
    encoded = _transform(message)
    for i in reversed(range(1, len(encoded)/5 + 1)):
        encoded.insert(i*5, ' ')
    return ''.join(encoded).strip()
