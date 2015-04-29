import re, string as s; pattern = re.compile('[\W_]+')

switch = s.maketrans(s.ascii_lowercase, s.ascii_lowercase[::-1])

def encode(phrase):
    x = s.translate(pattern.sub('', phrase.lower()), switch)
    return ' '.join(x[i:i+5] for i in range(0, len(x), 5))
    
def decode(phrase):
    return s.translate(pattern.sub('', phrase.lower()), switch)
