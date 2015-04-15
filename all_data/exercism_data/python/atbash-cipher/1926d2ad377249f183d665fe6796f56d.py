import string
def encode(s):
    #make a space every five characters
    s = decode(s)
    result = ""
    for index in range(0, len(s), 5):
        result += s[index:index+5] + ' ' * (index + 5 < len(s))#(' ' if index + 5 < len(s) else '')
    return result

def decode(s):
    #strip whitespace and punctuation, make lowercase while reverse mapping all characters
    return s.lower().translate(str.maketrans(
        string.ascii_lowercase,
        string.ascii_lowercase[::-1],
        string.punctuation.join(string.whitespace)))
