import string
encode_map = string.maketrans(string.lowercase, string.lowercase[::-1])
decode_map = string.maketrans(string.lowercase[::-1], string.lowercase)

def decode(text):
    return text.lower().translate(decode_map, string.punctuation + string.whitespace)
    
def encode(text):
    valid = set(string.whitespace) | set(string.punctuation)
    text = ''.join(ch for ch in text.lower() if ch not in valid).translate(encode_map)
    return ' '.join(text[x:x+5] for x in xrange(0, len(text), 5))
