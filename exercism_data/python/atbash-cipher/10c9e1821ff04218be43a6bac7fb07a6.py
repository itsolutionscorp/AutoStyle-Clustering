def encode(s):
    x = []
    for i in s:
       if 65 <= ord(i) <= 122:
           n = i.lower()
           z = ord(n)
           z -= 97
           x.append(chr(122-z))
       if i.isdigit():
           x.append(str(i))
    z = [x[i:i+5] for i in xrange(0, len(x), 5)]
    z = map(lambda x: ''.join(x), z)
    return ' '.join(z)

def decode(s):
    return ''.join(encode(s).split())
