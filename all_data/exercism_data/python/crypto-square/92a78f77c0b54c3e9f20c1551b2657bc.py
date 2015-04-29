import string

def encode(s):
    i = 1
    s = s.lower().translate(str.maketrans(
    string.ascii_lowercase,
    string.ascii_lowercase,
    string.punctuation.join(string.whitespace)))
    while i*i < len(s):
        i+=1
    result = ""
    for j in range(i*i):
        pos = (j//i) + (j%i) * i
        if pos < len(s):
            result += s[pos]
        if (len(result)+1) % 6 == 0:
            result+= ' '
    return result.rstrip()

def decode(s):
    s = s.translate(str.maketrans(
    string.ascii_lowercase,
    string.ascii_lowercase,
    string.whitespace))
    i = 1
    while i*i < len(s):
        i+=1
    rows = i
    while rows * i > len(s):
        rows-=1
    rows +=1
    result = ""
    difference = i*rows - len(s)
    for j in range(i-difference+1, i+1):
        s = s[:j*rows-1] + '*' + s[j*rows-1:]
    for j in range(i*rows):
        pos = (j//i) + (j%i) * rows
        if pos < len(s):
            result += s[pos]
    return result[:-difference]
