from string import ascii_lowercase, punctuation, maketrans

a = ascii_lowercase
backwards = a[::-1]
tr = maketrans(a, backwards)


def encode(text):
    text = text.translate(None, punctuation).lower().replace(' ', '')
    encL = []
    string = ''
    for c in text:
        if len(string) == 5:
            encL.append(string)
            string = c 
        elif len(string) < 5:
            string = string + c
    encL.append(string)

    return " ".join(encL).translate(tr)


def decode(text):
    decode = text.translate(None, punctuation).lower().replace(' ', '').translate(tr)
    return decode
