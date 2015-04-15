
ALPHABET = 'abcdefghijklmnopqrstuvwxyz'
ATBASH = str.maketrans(ALPHABET, ALPHABET[::-1], ' .,?!:;-_')

def encode(text):
    text = text.strip().lower().translate(ATBASH)
    return ' '.join(text[i*5 : (i+1)*5]
                    for i in range(len(text) // 5 + 1)).strip()

def decode(text):
    return text.strip().lower().translate(ATBASH)
