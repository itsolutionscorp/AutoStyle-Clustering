import string

def encode(line):
    line = line.lower().translate(str.maketrans('abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba', 
        string.whitespace))
    return ' '.join(line[i:i + 5] for i in range(0, len(line), 5))

def decode(line):
    return line
