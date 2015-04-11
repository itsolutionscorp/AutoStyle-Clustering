import string

def encode(line):
    line = line.lower().translate(str.maketrans('abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba', 
        string.whitespace + string.punctuation))
    return align_result(line)

def decode(line):
    return line.translate(str.maketrans('zyxwvutsrqponmlkjihgfedcba', 'abcdefghijklmnopqrstuvwxyz', string.whitespace))

def align_result(line):
    return ' '.join(line[i:i + 5] for i in range(0, len(line), 5))
