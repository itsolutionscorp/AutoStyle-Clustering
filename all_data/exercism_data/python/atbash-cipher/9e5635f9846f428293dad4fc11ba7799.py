from collections import defaultdict
import string

cipher = defaultdict(str, zip(
    string.lowercase + string.digits,
    string.lowercase[::-1] + string.digits
))

def decode(message):
    return ''.join(cipher[letter.lower()] for letter in message)

def encode(message):
    def whitespace(index):
        if index > 0 and index % 5 == 0:
            return ' '
        return ''

    return ''.join(
        whitespace(index) + letter
        for index, letter
        in enumerate(decode(message))
    )
