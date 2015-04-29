"""
atbash_cipher.py: Encodes and decodes strings based on atbash-cipher.
"""

_CIPHER = {
    'z': 'a',
    'y': 'b',
    'x': 'c',
    'w': 'd',
    'v': 'e',
    'u': 'f',
    't': 'g',
    's': 'h',
    'r': 'i',
    'q': 'j',
    'p': 'k',
    'o': 'l',
    'n': 'm',
    'm': 'n',
    'l': 'o',
    'k': 'p',
    'j': 'q',
    'i': 'r',
    'h': 's',
    'g': 't',
    'f': 'u',
    'e': 'v',
    'd': 'w',
    'c': 'x',
    'b': 'y',
    'a': 'z',
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


def encode(text_to_encode):
    """
    Function to encode text from argument using atbash-cipher. Returns encoded
    string.
    """
    encoded_text = []
    # break text into list for matching
    char_lst = list(text_to_encode.lower())
    # loop through our character list to match against cipher keys
    for char in char_lst:
        for k, v in _CIPHER.items():
            # if a match occurs, append it to our result list
            if char == k:
                encoded_text.append(v)
    # stringify our result list
    stringified_text = ''.join(encoded_text)
    # ensure there are spaces every five characters
    return ' '.join(stringified_text[i:i + 5] for i in range(0, len(stringified_text), 5))


def decode(text_to_decode):
    """
    Function to decode text from argument using atbash-cipher. Returns encoded
    string.
    """
    decoded_text = []
    # break text into list for matching
    char_lst = list(text_to_decode.lower())
    # loop through our character list to match against cipher keys
    for char in char_lst:
        for k, v in _CIPHER.items():
            # if a match occurs, append it to our result list
            if char == v:
                decoded_text.append(k)
    return ''.join(decoded_text)
