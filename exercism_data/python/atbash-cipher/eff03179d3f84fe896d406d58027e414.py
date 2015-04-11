plain  = 'abcdefghijklmnopqrstuvwxyz1234567890'
cipher = 'zyxwvutsrqponmlkjihgfedcba1234567890'

def encode(text):
    output = ''
    count = 1
    for i, text_char in enumerate(text.strip().replace(' ', '')):
        if not text_char.isalnum():
            continue
        for j, plain_char in enumerate(plain):
            if text_char.lower() == plain_char:
                output += cipher[j]
                break
        if count == 5:
            output += ' '
            count = 1
        else:
            count += 1

    return output.strip()

def decode(text):
    text = text.strip().replace(' ', '')
    output = ''
    for i, text_char in enumerate(text):
        for j, cipher_char in enumerate(cipher):
            if text_char.lower() == cipher_char:
                output += plain[j]
    return output
