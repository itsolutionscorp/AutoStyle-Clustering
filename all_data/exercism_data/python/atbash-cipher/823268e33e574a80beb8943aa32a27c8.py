plain  = 'abcdefghijklmnopqrstuvwxyz'
cipher = 'zyxwvutsrqponmlkjihgfedcba'

def encode(plain_str):
    plain_case = ''.join(plain_str.replace(',','').lower()
                         .strip('.!?').split())
    enc_str = ''
    for i, char in enumerate(plain_case):
        if i > 0 and i % 5 == 0:
            enc_str += ' '
        if char not in plain:
            enc_str += char
        else:
            enc_str += cipher[plain.find(char)]
    return enc_str

def decode(cipher_str):
    cipher_joint = cipher_str.replace(' ', '')
    dec_str = ''
    for char in cipher_joint:
        dec_str += plain[cipher.find(char)]
    return dec_str
