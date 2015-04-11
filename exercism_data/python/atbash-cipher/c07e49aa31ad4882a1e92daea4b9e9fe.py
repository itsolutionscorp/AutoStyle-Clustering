import string

translation_table = "".maketrans(string.ascii_letters, 
                                 string.ascii_lowercase[::-1] * 2,
                                 string.punctuation + string.whitespace)

def cipher(text):
    return text.translate(translation_table)

def inserter(iterable, char, dist):
    for i, c in enumerate(iterable):
        if i > 0 and not i % dist:
            yield(char)
        yield(c)
        
    
def encode(text):
    encoded = cipher(text)
    return "".join(inserter(encoded, " ", 5))

def decode(text):
    return "".join(cipher(text))
