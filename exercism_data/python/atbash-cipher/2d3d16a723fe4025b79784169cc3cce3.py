import string

def encode(s):
    toAtbash = string.maketrans(
        'abcdefghijklmnopqrstuvwxyz',
        'zyxwvutsrqponmlkjihgfedcba')
    s = s.lower().replace(" ", "").replace(".", "").replace(',', "")
    n = 5
    slist = [s[i:i+n] for i in range(0, len(s), n)]
    slist = ' '.join(slist)
    return slist.translate(toAtbash)

def decode(s):
    toNormal = string.maketrans(
        'zyxwvutsrqponmlkjihgfedcba',
        'abcdefghijklmnopqrstuvwxyz')
    s = s.replace(" ", "")
    return s.translate(toNormal)

