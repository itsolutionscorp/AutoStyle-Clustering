import string

plain = string.ascii_lowercase
cipher = ''.join(reversed(plain))
trans_table = str.maketrans(plain, cipher)

def decode(text):
    return ''.join([
        chunk.translate(trans_table)
        for chunk in text.split()
    ])

def encode(text):
    stripped = ''.join([
        x for x in text.lower()
        if x not in string.whitespace
        and x not in string.punctuation
    ])
    trans = stripped.translate(trans_table)
    chunks = [trans[i:i+5] for i in range(0, len(trans), 5)]
    return ' '.join(chunks)
