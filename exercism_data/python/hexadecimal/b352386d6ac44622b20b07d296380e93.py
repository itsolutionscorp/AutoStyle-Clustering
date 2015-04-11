def hexa(text):
    text = text.lower()
    smap = "0123456789abcdef"
    if (not type(text) is str) or (any(c not in smap for c in text)):
        raise ValueError("String has to represent a binary string")
    return sum(smap.index(c)*16**(idx) for idx,c in enumerate(text[::-1]))
