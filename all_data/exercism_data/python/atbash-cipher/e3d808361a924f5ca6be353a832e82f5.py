import string

trans = string.maketrans("abcdefghijklmnopqrstuvwxyz","zyxwvutsrqponmlkjihgfedcba")

def encode(s):
    raw = s.lower().translate(trans, ",. ")
    encoded = ""
    wl = 5
    for i in range((len(raw) / wl)+1):
        encoded += raw[i*wl:i*wl+wl] + " "
    return encoded.strip()

def decode(s):
    return s.lower().translate(trans, " ")
