alphabet = 'abcdefghijklmnopqrstuvwxyz'
ciphered = alphabet[::-1]

def chunks(items, chunk_length):
    return [items[i:i + chunk_length] for i in xrange(0, len(items), chunk_length)]

def translate(alpha, cipher, sequence, chunk_length=1, delim=''):
    translated = ''
    for char in sequence:
        if char.isalpha():
            translated += alpha[cipher.index(char.lower())]
        elif char.isdigit():
            translated += char

    return delim.join(chunks(translated, chunk_length))

def decode(sequence):
    return translate(ciphered, alphabet, sequence)

def encode(sequence):
    return translate(alphabet, ciphered, sequence, chunk_length=5, delim=' ')
