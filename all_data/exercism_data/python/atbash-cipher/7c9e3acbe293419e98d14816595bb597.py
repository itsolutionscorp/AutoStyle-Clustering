import string

cipher = string.ascii_lowercase
map = {}

for x in range(len(cipher)):
    map[cipher[x]] = cipher[-x - 1]


def decode(input):
    input = filter(str.isalnum, input.lower())

    encoded = ""
    for letter in input:
        if letter.isalpha():
            encoded += map[letter]
        else:
            encoded += letter
    return encoded


def encode(input):
    encoded = decode(input)
    return " ".join([encoded[i:i + 5] for i in range(0, len(encoded), 5)])
