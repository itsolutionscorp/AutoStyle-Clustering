from string import punctuation, whitespace, ascii_lowercase

alphabet = ascii_lowercase
CHUNK_SIZE = 5
atbash_mapping = str.maketrans(alphabet, alphabet[::-1], whitespace + punctuation)


def translate(text):
    return text.lower().translate(atbash_mapping)


def decode(cipher):
    translated = translate(cipher)
    return translated


def encode(plain_text):
    translated = translate(plain_text)
    chunked = [
        translated[i:i + CHUNK_SIZE]
        for i in range(0, len(translated), CHUNK_SIZE)
    ]
    return " ".join(chunked)
