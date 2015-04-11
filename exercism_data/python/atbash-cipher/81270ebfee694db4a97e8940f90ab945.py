import string

alpha = string.ascii_lowercase
revalpha = alpha[::-1]

def cleanup(text):
    return text.translate(None,string.whitespace+string.punctuation).lower()

# Encipher text one letter at a time and add a space very fifth letter
def encode(plain):
    cipher = ""
    for i, letter in enumerate(cleanup(plain)):
        # Add space every fifth letter
        if i > 0 and i % 5 == 0: 
            cipher += ' '
        # Ignore digits when enciphering letters
        if letter in string.letters:
            cipher += revalpha[alpha.find(letter)]
        else:
            cipher += letter
            
    return cipher

# Return plain text, ignoring any spaces in cipher text
def decode(cipher):
    return ''.join([alpha[revalpha.find(letter)] for letter in cipher if not letter == ' '])
