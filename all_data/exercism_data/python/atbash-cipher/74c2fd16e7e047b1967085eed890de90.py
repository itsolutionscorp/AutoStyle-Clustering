from string import lowercase, uppercase, punctuation, maketrans, whitespace

_plain_alphabet = lowercase + uppercase
_cipher_alphabet = lowercase[::-1] * 2
_filtered_characters = punctuation + whitespace
_cipher_map = maketrans(_plain_alphabet, _cipher_alphabet)

def encode(message):
    ciphertext = message.translate(_cipher_map, _filtered_characters)
    return _separate_words(ciphertext, 5)

def decode(message):
    return message.translate(_cipher_map, _filtered_characters)

def _separate_words(message, wordsize):
    words = (
        message[i: i+wordsize]
        for i in xrange(0, len(message), wordsize)
    )
    return " ".join(words)
