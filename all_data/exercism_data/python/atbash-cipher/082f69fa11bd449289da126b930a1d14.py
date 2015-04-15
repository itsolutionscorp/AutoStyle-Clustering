from string import ascii_lowercase


def encode(text):
    code = ""
    for i in text.lower():
        if i.isalpha():
            code += ascii_lowercase[::-1][ascii_lowercase.find(i.lower())]
        elif i.isdigit():
            code += i
    return " ".join(code[i:i + 5] for i in xrange(0, len(code), 5))


def decode(text):
    return encode(text).replace(" ", "")
