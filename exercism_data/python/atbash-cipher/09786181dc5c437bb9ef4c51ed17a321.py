def encode(line):
    return line.translate(str.maketrans('abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba'))


def decode(line):
    return line
