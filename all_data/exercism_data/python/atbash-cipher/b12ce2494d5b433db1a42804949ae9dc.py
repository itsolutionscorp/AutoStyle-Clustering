def encode(decoded):
    encoded = decoded.lower()
    encoded = (i for i in encoded if i.isalnum())
    encoded = (chr(219-ord(i)) if 97 <= ord(i) <= 122 else i for i in encoded)
    encoded = ''.join(value if key%5 or key==0 else ' '+value for key,value in enumerate(encoded))
    return encoded

def decode(encoded):
    decoded = (chr(219-ord(i)) if 97 <= ord(i) <= 122 else i for i in encoded)
    decoded = ''.join(i for i in decoded if i.isalnum())
    return decoded
