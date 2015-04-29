import string
n = 5
def encode(msg):
    msg = filter(lambda c: c in string.lowercase + string.digits,
                  msg.lower())
    cipher_map = {p: e for p, e in
            zip(string.lowercase,
                reversed(string.lowercase))}
    cipher = [cipher_map.get(c, c) for c in msg]
    return ' '.join(
            [''.join(cipher[i:i+n]) for i in range(0, len(cipher), n)])

def decode(msg):
    return encode(msg).replace(' ', '')
