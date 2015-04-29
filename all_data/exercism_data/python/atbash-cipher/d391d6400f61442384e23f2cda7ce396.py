import string

plain = string.ascii_lowercase
cipher = ''.join(reversed(plain))
trans_table = str.maketrans(plain, cipher)
strip_table = {
    ord(i): None
    for i in string.whitespace + string.punctuation
}

def decode(text):
    return ''.join([
        chunk.translate(trans_table)
        for chunk in text.split()
    ])

def encode(text):
    text = text.lower()\
        .translate(strip_table)\
        .translate(trans_table)
    return ' '.join(text[i:i+5] for i in range(0, len(text), 5))
