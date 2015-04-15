from string import ascii_lowercase, digits, maketrans, translate, whitespace, punctuation
 
def xcode(text, space=None):
    xlate = maketrans(ascii_lowercase + digits, ascii_lowercase[::-1] + digits)
    out = translate(text.lower(), xlate, whitespace + punctuation)
    if space:
        tmp = ""
        for i in range(len(out))[::space]:
            tmp += (out[i:i+space] + " ")
        out = tmp.rstrip()
    return out

encode = lambda x: xcode(x, 5)
decode = lambda x: xcode(x)
