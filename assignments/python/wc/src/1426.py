import string
def word_count(line):
    separators = string.whitespace + ",:;./\\"
    punctuation = "`~!@#$%^&*()-_=+[]{}"
    line = line.lower()
    d = {}
    for p in punctuation:
        line = line.replace(p, "")
    for s in separators:
        line = line.replace(s, " ")
    for w in line.split():
        d[w] = d.get(w, 0) + 1
    return d
