from string import ascii_lowercase, maketrans

def cipher(message):
    # first remove all non-alphanumeric characters from the message
    message = ''.join(c for c in message.lower() if c.isalnum())
    # then substitute
    return message.translate(maketrans(ascii_lowercase, ascii_lowercase[::-1]))
    
def encode(message):
    ciphertext = cipher(message)
    # divide ciphertext in groups of 5 characters, separated by space
    return ' '.join(ciphertext[i:i+5] for i in range(0,len(ciphertext),5))
    
def decode(message):
    return cipher(message)
