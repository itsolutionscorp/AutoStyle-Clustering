import string

def encode(str):
    code = dict(zip(reversed(string.ascii_lowercase),string.ascii_lowercase))
    code.update(dict(zip(string.digits,string.digits)))
    str = str.lower()
    encoded = ""
    x = 0
    for v in str:
        if v in code:
            encoded += code[v]
            x += 1
            if x%5 == 0 and x != 0:
                encoded += " "
    return encoded.strip()
        
    pass

def decode(str):
    code = dict(zip(reversed(string.ascii_lowercase),string.ascii_lowercase))
    code.update(dict(zip(string.digits,string.digits)))
    str = str.lower()
    decoded = ""
    for x,v in enumerate(str):
        if v in code:
            decoded += code[v]
    return decoded.strip()
    pass
