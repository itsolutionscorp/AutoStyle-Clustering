import string

trans_table = str.maketrans(string.ascii_lowercase,
                            string.ascii_lowercase[::-1],
                            string.punctuation)


def encode(text):
    text = text.lower().replace(" ", "")
    trans_text = text.translate(trans_table)
    result = ""
    for i, c in enumerate(trans_text):
        if (i != 0 and i % 5 == 0):
            result += " " + c
        else:
            result += c
    return result


def decode(text):
    text = text.lower().replace(" ", "")
    trans_text = text.translate(trans_table)
    return trans_text
