from string import ascii_lowercase, maketrans, punctuation, whitespace

def encode(text):
    translator = maketrans(ascii_lowercase + punctuation, ascii_lowercase[::-1] + " " * len(punctuation))
    text = text.lower().translate(translator).translate(None, whitespace)
    return ' '.join(text[i:i+5] for i in range(0, len(text), 5))

def decode(text):
    translator = maketrans(ascii_lowercase[::-1], ascii_lowercase)
    return text.lower().replace(" ","").translate(translator)
