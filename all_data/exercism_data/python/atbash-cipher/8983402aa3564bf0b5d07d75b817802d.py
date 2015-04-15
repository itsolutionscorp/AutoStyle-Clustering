PLAIN = 'abcdefghijklmnopqrstuvwxyz'
CIPHER = 'zyxwvutsrqponmlkjihgfedcba'

PLAIN_TO_CIPHER = str.maketrans(PLAIN, CIPHER)
CIPHER_TO_PLAIN = str.maketrans(CIPHER, PLAIN)


def decode(ciphertext):
    return clean_input(ciphertext).translate(CIPHER_TO_PLAIN)


def encode(plaintext):
    return format_ciphertext(clean_input(plaintext.lower()).translate(PLAIN_TO_CIPHER))


def format_ciphertext(in_string):
    # return value is the in_string in space separated blocks of 5
    return ' '.join([in_string[i: i+5] for i in range(0, len(in_string), 5)])


def clean_input(in_string):
    # toss out anything non alphanumeric
    return ''.join(char for char in in_string if char.isalnum())
