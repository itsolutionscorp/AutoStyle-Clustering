import string

encode_map = dict(zip(string.ascii_lowercase, string.ascii_lowercase[::-1]))
exclude = lambda c: c not in string.punctuation and c not in string.whitespace

def spacify(s):
    return ' '.join(s[x:y] for x, y in zip(range(0, len(s)+5, 5), range(5, len(s)+5, 5)))

def decode(str):
    return ''.join([encode_map[c.lower()] if c.isalpha() else c for c in filter(exclude, str)])

def encode(str):
    return spacify(decode(str))
