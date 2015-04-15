alpha = "".join([chr(x) for x in range(ord('a'), ord('z')+1)])
table = str.maketrans(alpha, alpha[::-1], " ")


def decode(s):
    return s.lower().translate(table)


def encode(s):
    # inelegant
    s = "".join((filter(lambda x: x.isdigit() or x.isalpha(), s)))
    return space(s.lower().translate(table))


def space(s):
    if len(s) < 6:
        return s
    return s[:5] + " " + space(s[5:])
