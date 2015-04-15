PLAIN = 'abcdefghijklmnopqrstuvwxyz'
CIPHER = 'zyxwvutsrqponmlkjihgfedcba'

def decode(s):
    return ''.join(PLAIN[CIPHER.find(c.lower())] for c in s if c != ' ')

def encode(s):
    message = ''
    i = 0
    for c in s:
        if c in [',', '.', ' ']:
            continue
        if i != 0 and i % 5 == 0:
            message += ' '
        if c.lower() in PLAIN:
            message += CIPHER[PLAIN.find(c.lower())]
        else:
            message += c
        i += 1
    return message

if __name__ == '__main__':
    print encode('mindblowingly')
    print decode('vcvix rhn')
