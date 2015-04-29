plain = 'abcdefghijklmnopqrstuvwxyz'
cipher = 'zyxwvutsrqponmlkjihgfedcba'    

def encode(text):
    encoded = []
    text = ''.join([x.strip(' ') for x in text]).lower()
    for idx in range(len(text)):
        if text[idx] in plain:
            encoded.append(cipher[plain.index(text[idx])])
        elif (text[idx].isdigit()):
            encoded.append(text[idx])
    encoded = ''.join(encoded)
    if len(encoded) > 5:
        encoded = [encoded[i:i+5] for i in range(0,len(encoded),5)]
        encoded = ' '.join(encoded)
    return encoded

def decode(text):
    decoded = []
    text = "".join([x.strip(' ') for x in text])
    for idx in range(len(text)):
        if text[idx] in cipher:
            decoded.append(plain[cipher.index(text[idx])])
        elif (text[idx].isdigit()):
            decoded.append(text[idx])
    return ''.join(decoded)
