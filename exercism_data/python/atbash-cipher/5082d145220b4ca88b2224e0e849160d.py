from string import punctuation, digits 

PLAIN = "abcdefghijklmnopqrstuvwxyz"
CIPHER = PLAIN[::-1]


def encode(plaintext):
 
    encoded = []
    for c in plaintext.lower():
        if c in digits:
            encoded.append(c)
        elif c not in punctuation and c != ' ':
            encoded.append(CIPHER[PLAIN.index(c)])

    encoded = ''.join(encoded)
    
    result = []
    if len(encoded) > 4: 
        i = 0

        while i + 5 <= len(encoded):
           result.append(encoded[i:i+5])
           i += 5
        if encoded[i:] != '':
            result.append(encoded[i:])
        ans = ' '.join(result)
    else:
        ans = encoded           
                    
    return ans


def decode(encoded):
    encoded = [c for c in encoded if c != ' ']
    return ''.join([PLAIN[CIPHER.index(c)] for c in encoded])
