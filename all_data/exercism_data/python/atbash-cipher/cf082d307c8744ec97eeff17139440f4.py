from string import ascii_lowercase
trans = str.maketrans(dict(zip(ascii_lowercase, reversed(ascii_lowercase))))

def remove_nonalnum(s):
    return filter(lambda c: c.isalnum(), s)

def insert_spaces(s, n):
    for i, c in enumerate(s):
        yield c
        if not (i+1)%5:
            yield ' '

def remove_spaces(s):
    return filter(lambda c: not c.isspace(), s)

def encode(message):
    return ''.join(insert_spaces(remove_nonalnum(message.lower().translate(trans)), 5)).strip()

def decode(message):
    return ''.join(remove_spaces(message)).translate(trans)
