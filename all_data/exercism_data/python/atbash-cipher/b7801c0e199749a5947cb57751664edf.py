alphabet = "abcdefghijklmnopqrstuvwxyz"
reverse_alphabet = "zyxwvutsrqponmlkjihgfedcba"
CHUNK_SIZE = 5


def translate(text, operation):
    if operation == 'decode':
        return text.lower().translate(str.maketrans(reverse_alphabet, alphabet, " .,"))
    else:
        return text.lower().translate(str.maketrans(alphabet, reverse_alphabet, " .,"))


def decode(cipher):
    translated = translate(cipher, 'decode')
    return translated


def encode(plain_text):
    translated = translate(plain_text, 'encode')
    chunked = " ".join([translated[i:i + CHUNK_SIZE] for i in range(0, len(translated), CHUNK_SIZE)])
    return chunked
