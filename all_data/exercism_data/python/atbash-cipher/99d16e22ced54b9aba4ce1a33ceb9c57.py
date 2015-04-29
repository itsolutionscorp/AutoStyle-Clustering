def cipher(text):
    oa, oz = ord("a"), ord("z")
    return [c.isalpha() and chr(oa + oz - ord(c.lower())) or c 
                for c in text if c.isalnum()]

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
