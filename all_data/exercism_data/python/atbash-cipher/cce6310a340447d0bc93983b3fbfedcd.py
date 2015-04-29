import string

translation_table = "".maketrans(string.ascii_letters, 
                                 string.ascii_lowercase[::-1] * 2,
                                 string.punctuation + string.whitespace)

def cipher(text):
    return text.translate(translation_table)
    
def encode(text):
    encoded = cipher(text)
    return " ".join(encoded[i:i + 5] for i in range(0, len(encoded), 5))

def decode(text):
    return "".join(cipher(text))
