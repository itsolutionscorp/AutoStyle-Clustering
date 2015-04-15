plain  = 'abcdefghijklmnopqrstuvwxyz'
cipher = 'zyxwvutsrqponmlkjihgfedcba'

def encode(plain_str):
    plain_case = ''.join(plain_str.replace(',','').lower()
                         .strip('.!?').split())
    plain_case_enc = plain_case.translate(str.maketrans(plain, cipher))
    enc_str = ''
    for i, char in enumerate(plain_case_enc):
        if i > 0 and (i+1) % 5 == 0:
            enc_str = char.join([enc_str, ' '])
        else:
            enc_str = ''.join([enc_str, char])
    return enc_str.strip()

def decode(cipher_str):
    dec_str = cipher_str.replace(' ', '').translate(str.maketrans(cipher,
                                                                  plain))
    return dec_str
