def parse_binary(text):
    if (not type(text) is str) or (any(c not in "10" for c in text)):
        raise ValueError("String has to represent a binary string")
    return sum(2**(idx) for idx,c in enumerate(text[::-1]) if c == "1")
