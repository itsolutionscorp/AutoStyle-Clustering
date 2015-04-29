def atbash_helper(text, enc=False):
    text = text.lower()
    res = ""
    count = 0
    for c in text:
        if c.isalpha():
            ord2 = 26 + ord('a') - (ord(c) - ord('a') + 1)
            res += chr(ord2)
        elif c.isdigit():
            res += c
        else:
            continue
        if enc:
            count += 1
            if count % 5 == 0:
                res += ' '
    return res.rstrip()

def encode(text):
    return atbash_helper(text, enc=True)

def decode(text):
    return atbash_helper(text)
