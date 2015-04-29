from string import lowercase, uppercase, digits, punctuation, maketrans, whitespace

_plain_alphabet = lowercase + digits
_cipher_alphabet = lowercase[::-1] + digits
_filtered_characters = punctuation + whitespace

_cipher_map = maketrans(_plain_alphabet, _cipher_alphabet)
_lower_map = maketrans(uppercase, lowercase)

def encode(message):
    normalized = _normalize(message)
    ciphertext = normalized.translate(_cipher_map)
    return _separate_words(ciphertext, 5)

def decode(message):
    normalized = _normalize(message)
    return normalized.translate(_cipher_map)

def _normalize(message):
    return message.translate(_lower_map, _filtered_characters)

def _separate_words(message, wordsize):
    words = (
        message[i: i+wordsize]
        for i in xrange(0, len(message), wordsize)
    )
    return " ".join(words)
