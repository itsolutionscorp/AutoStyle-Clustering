import string

abc = string.ascii_lowercase
zyx = list(reversed(abc))

def decode(code):
    msg = ""
    for c in code:
        if c.isalpha():
            msg += abc[zyx.index(c)]
        elif c.isdigit():
            msg += c

    return msg

def encode(msg):
    code = ""
    for c in msg.lower():
        if c.isalpha():
            code += zyx[abc.index(c)]
        elif c.isdigit():
            code += c

        if (len(code) + 1) % 6 == 0:
            code += ' '

    return code.strip()
