import string

def encode(s):
    return decode(s, mode="encode")

def decode(s, mode="decode"):
    s = s.lower().translate(str.maketrans(
        string.ascii_lowercase,
        string.ascii_lowercase,
        string.punctuation.join(string.whitespace)))
    square = 1
    while square * square < len(s):
        square += 1
    result = ""
    rows = square
    difference = 0
    if mode == "decode":
        rows -= square * square > len(s)
        difference = square * rows - len(s)
        #pad the string so that the square can be mathematically traveled
        for j in range(square - difference + 1, square + 1):
            s = s[:j * rows - 1] + '*' + s[j * rows - 1:]
    for j in range(square * rows):
        pos = (j // square) + (j % square) * rows
        if pos < len(s):
            result += s[pos]
        if mode == "encode":
            if (len(result) + 1) % 6 == 0:
                result += ' '
    return result[:-difference if mode == "decode" else len(result) + 1].rstrip()
