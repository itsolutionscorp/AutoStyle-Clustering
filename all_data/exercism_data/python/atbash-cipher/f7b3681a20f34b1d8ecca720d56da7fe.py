import string

atbash = string.maketrans(string.ascii_lowercase, string.ascii_lowercase[::-1])

def encode(source):
    result = "".join(i for i in source.lower() if i.isalnum()).translate(atbash)
    return ' '.join([result[i:i+5] for i in range(0, len(result), 5)])

def decode(source):
    return "".join(i for i in source.lower() if i.isalnum()).translate(atbash)
