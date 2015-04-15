import string

def encode(line):
    return line.lower().translate(str.maketrans('abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba', 
        string.whitespace))

def decode(line):
    return line
