Plain = "abcdefghijklmnopqrstuvwxyz0123456789"
Cipher = "zyxwvutsrqponmlkjihgfedcba0123456789"


def addspace(loc, s):
    spaced = s[0:loc]
    for i in range(loc, len(s), loc):
        spaced += " " + s[i:(i + loc)]
    return spaced.strip(" ")


def encode(s):
    encoded_s = s.lower().translate(str.maketrans(Plain, Cipher, " .,"))
    return(addspace(5, encoded_s))


def decode(s):
    decoded_s = s.lower().translate(str.maketrans(Cipher, Plain, " .,"))
    return decoded_s
