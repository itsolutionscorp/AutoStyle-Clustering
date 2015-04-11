from string import ascii_lowercase

atbash = str.maketrans(ascii_lowercase, ascii_lowercase[::-1])

def encode(text):
    text = ''.join([c.lower() for c in text if c.isalnum()]).translate(atbash)
    text = [text[i:i+5] for i in range(0, len(text), 5)]

    return ' '.join(text)


def decode(text):
    return ''.join(text.translate(atbash).split())
