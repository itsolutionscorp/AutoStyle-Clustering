from math import ceil


def encode(plain):
    plain = "".join([letter.lower()
                     for letter in plain
                     if letter.isalpha() or letter.isdigit()])
    row_len = ceil(len(plain) ** .5)
    return " ".join([plain[i::row_len] for i in range(row_len)])
