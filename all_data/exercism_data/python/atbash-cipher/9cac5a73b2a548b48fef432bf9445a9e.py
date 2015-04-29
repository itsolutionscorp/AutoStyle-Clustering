from string import ascii_letters, ascii_lowercase, ascii_uppercase

### Utility functions ###
def group(text):
    return ' '.join(text[i:i+5] for i in range(0, len(text), 5))

def reverse(s):
    return ''.join(reversed(s))

def translate(text):
    return text.translate(CIPHER)

CIPHER=str.maketrans(ascii_letters,
                     reverse(ascii_lowercase) + reverse(ascii_uppercase))

### Main cipher functions ###
def decode(ciphertext):
    return translate(ciphertext.replace(' ', ''))

def encode(plaintext):
    alpha_plaintext = ''.join(c for c in plaintext.lower() if c.isalnum())
    print(alpha_plaintext)
    return group(translate(alpha_plaintext))
