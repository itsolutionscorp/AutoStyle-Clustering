def encode(line):
    return line.lower().translate(str.maketrans('abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba'))


def decode(line):
    return line
