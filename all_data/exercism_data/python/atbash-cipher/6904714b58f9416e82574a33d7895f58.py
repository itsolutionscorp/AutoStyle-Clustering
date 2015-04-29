def decode(s):
    s = filter(str.isalnum, s.lower())

    return "".join(chr(219-ord(c)) if c.isalpha() else c for c in s)

def encode(s):
    s = filter(str.isalnum, s.lower())
    ds = decode(s)

    return " ".join(ds[i:i+5] for i in range(0, len(ds), 5))
