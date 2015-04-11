from string import ascii_lowercase

def reverse(text):
    return str.maketrans(ascii_lowercase, ascii_lowercase[::-1])

def decode(text):
    return ''.join([c for c in text if c.isalnum()]).translate(reverse(text))

def encode(text):
    encoded = ''.join([c for c in text
                       if c.isalnum()]).lower().translate(reverse(text))
    return ' '.join([encoded[c:c + 5]
                     for c in range(0, len(encoded), 5)])
