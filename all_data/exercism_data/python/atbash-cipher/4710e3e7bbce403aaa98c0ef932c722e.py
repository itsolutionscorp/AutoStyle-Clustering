from string import ascii_letters, ascii_lowercase, maketrans

atbash = maketrans(ascii_letters, ascii_lowercase[::-1] + ascii_lowercase[::-1])

def encode(source):
    result = "".join(i for i in source if i.isalnum()).translate(atbash)
    return ' '.join(result[i:i+5] for i in range(0, len(result), 5))

def decode(source):
    return "".join(i for i in source if i.isalnum()).translate(atbash)
