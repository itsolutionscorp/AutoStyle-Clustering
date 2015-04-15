import string
from string import maketrans

TRANS_KEY = 'zyxwvutsrqponmlkjihgfedcba'

def decode(cipherText):
    # Remove spaces and make all letters lowercase.
    formattedCipherText = ' '.join(cipherText.lower().split()).replace(' ', '')
    # Decode text.
    return formattedCipherText.translate(maketrans(string.lowercase, TRANS_KEY))

def encode(plainText):
    # Remove spaces and make all letters lowercase.
    formattedText = (plainText.translate(
        string.maketrans('', ''), string.punctuation).lower())
    formattedText = ' '.join(formattedText.split()).replace(' ', '')

    # Encode text.
    cipherText = formattedText.translate(maketrans(TRANS_KEY, string.lowercase))

    # Break string into a list of characters to insert spaces every fifth index.
    cipherSeq = ' '.join(cipherText).split()
    # Counter to offset the index when spaces are inserted into the list.
    indexOffset = 0
    for i in range(1, len(cipherSeq)):
        # Add space every five elements.
        if i%5 == 0:
            cipherSeq.insert(i + indexOffset, ' ')
            indexOffset += 1
    return ''.join(cipherSeq)
