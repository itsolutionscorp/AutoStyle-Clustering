import string

alphabet = string.ascii_lowercase
reverse_alphabet = list(reversed(alphabet))
digits = string.digits

def encode(message):
    sanitized = __sanitize(message)
    coded = __swap_letters(sanitized)
    if (len(coded) < 6):
        return coded
    chunked = __chunk(coded)
    return chunked

def decode(message):
    sanitized = __sanitize(message)
    return __swap_letters(sanitized)

def __sanitize(message):
    return message.lower().replace(' ', '')

def __swap_letters(message):
    return "".join((__find_reverse_character(letter) for letter in message if letter.isalnum()))

def __find_reverse_character(char):
    if (char.isdigit()):
        return char
    i = alphabet.index(char)
    return reverse_alphabet[i]

def __chunk(message):
    chunks = [message[i:i+5] for i in range(0, len(message), 5)]
    return " ".join(chunks)
