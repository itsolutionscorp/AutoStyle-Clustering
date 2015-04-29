keys = {}
for i in xrange(26): keys[chr(122-i)] = chr(i+97)
for i in xrange(10): keys[str(i)] = str(i)

def decode(s):
    return ''.join([keys[letter.lower()] for letter in s if letter.lower() in keys])

def encode(s):
    s = [keys[letter.lower()] for letter in s if letter.lower() in keys]
    return ' '.join([''.join(s[i:i+5]) for i in xrange(0,len(s),5)])
