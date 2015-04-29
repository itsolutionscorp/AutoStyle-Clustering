import string

alphabet = string.ascii_lowercase
reverse = alphabet[::-1]

def sanitize(string):
    string = string.lower()
    return ''.join([char for char in string if char.isalnum()])

def code(original, spaces=False):
    original = sanitize(original)

    message = ''
    for i in xrange(len(original)):
        if original[i].isalpha():
            message += reverse[alphabet.index(original[i])]
        else:
            message += original[i]
        if spaces and (i + 1) % 5 == 0 and i != len(original) - 1:
            message += ' '
    return message

def decode(original):
    return code(original)
    
def encode(original):
    return code(original, spaces=True)
