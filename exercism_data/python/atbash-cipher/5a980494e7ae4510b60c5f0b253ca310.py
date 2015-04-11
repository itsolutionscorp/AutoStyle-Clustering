import string

ALPHABET = string.ascii_lowercase
TABLE = string.maketrans(ALPHABET, ALPHABET[::-1])

def encode(message):
    spaceless = [c for c in message.lower().translate(TABLE) if c.isalnum()]
    spaced = [''.join(spaceless)[i:i+5] for i in range(0, len(message), 5)]
    return ' '.join(spaced).strip()

def decode(code):
    code = ''.join([c for c in code.lower().translate(TABLE) if c.isalnum()])
    return code.strip()
