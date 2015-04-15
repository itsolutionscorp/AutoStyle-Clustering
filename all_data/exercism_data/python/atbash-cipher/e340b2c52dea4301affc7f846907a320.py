# transportion works the same in both directions
# so code() is a shared method that both code() and decode() use
def code(input):
    # define constants 
    alphabet = 'abcdefghijklmnopqrstuvwxyz'

    output = ''
    # convert to lowercase
    input = input.lower()
    # remove spaces
    input = input.replace(' ','')
    
    for char in input:
        # digits get passed through as is
        if char.isdigit():
            output += char
        # alphabet characters get transposed
        elif char.isalpha():
            output += (alphabet[-alphabet.index(char) - 1])
        # anything else is dropped
    return output


def decode(ciphertext):
    return code(ciphertext)


# encoded text has a space between every 5 characters
def encode(plaintext):
    encoded = code(plaintext)
    formatted = ''
    # step through the plaintext
    # every 5 chars add a space
    for i in range(0, len(encoded), 5):
        formatted += encoded[i:i + 5] + ' '
    # remove the trailing space
    return formatted.rstrip()
