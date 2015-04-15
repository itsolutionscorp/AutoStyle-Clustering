import re
import string

alphabet = string.ascii_letters


def decode(cipher_text):
    cipher_text = cipher_text.replace(' ', '')
    clear_text = ''
    for i in cipher_text:
        try:
            clear_text += alphabet[26 - 1 - alphabet.index(i)]
        except:
            print(i)
    return clear_text


def encode(clear_text):
    clear_text = clear_text.replace(' ', '')
    cipher_text = ''
    for i in clear_text.lower():
        try:
            cipher_text += alphabet[26 - 1 - alphabet.index(i)]
        except ValueError:
            try:
                if int(i) in range(0, 9):
                    cipher_text += i
            except:
                pass
    return re.sub(r'([a-z0-9]{5})(?!$)', r'\1 ', cipher_text)
