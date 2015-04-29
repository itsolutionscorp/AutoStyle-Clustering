import string
atbash = str.maketrans(dict(zip(string.ascii_lowercase, reversed(string.ascii_lowercase))))

def decode(what):
    return "".join(i for i in what.lower() if i.isalnum()).translate(atbash)

def encode(what):
    result = "".join(i for i in what.lower() if i.isalnum()).translate(atbash)
    return " ".join([result[i:i+5] for i in range(0, len(result), 5)])
