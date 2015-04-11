alpha = 'abcdefghijklmnopqrstuvwxyz'

def encode(plaintext):
    text = plaintext.lower().replace(' ','')
    char_buffer = []
    counter = 0
    for i in range(len(text)):
        if text[i] in alpha:
            char = alpha[-1 * (alpha.index(text[i]) + 1)]
        elif text[i] in '0123456789':
            char = text[i]
        else:
            continue
        if counter == 5:
            char_buffer.append(' ')
            counter = 0
        char_buffer.append(char)
        counter += 1


    return ''.join(char_buffer)


def decode(ciphertext):
    text = encode(ciphertext)
    return text.replace(' ','')
