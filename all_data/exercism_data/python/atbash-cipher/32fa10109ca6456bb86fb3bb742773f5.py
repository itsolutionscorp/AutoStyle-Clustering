from string import maketrans

en = maketrans('abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba')
de = maketrans('zyxwvutsrqponmlkjihgfedcba', 'abcdefghijklmnopqrstuvwxyz')

def decode(text):
    code = ''.join([i for i in text.lower() if i.isalpha()])
    return code.translate(de)

def encode(text):
    code = ''.join([i for i in text.lower() if i.isalpha() or i.isdigit()])
    ciphertext = list(code.translate(en))

    k = 1
    while k < len(ciphertext):
        if k % 5 == 0:
            ciphertext.insert(k + k / 5 - 1, ' ')
        k+=1
    while ciphertext[len(ciphertext) - 1] == ' ':
        del ciphertext[len(ciphertext) - 1]
    return ''.join(ciphertext)
