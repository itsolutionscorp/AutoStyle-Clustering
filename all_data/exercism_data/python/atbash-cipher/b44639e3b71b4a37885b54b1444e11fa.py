def encode(message):
    message = message.lower()
    return ''.join(add_spaces_evenly(convert_list(message)))


def decode(code):
    code = code.lower()
    return ''.join(convert_list(code))


def change_char(c):
    alphabet = "abcdefghijklmnopqrstuvwxyz"

    if c.isdigit():
        return c
    if c.isalpha():
        return alphabet[25 - alphabet.find(c)]
    return None


def convert_list(text):
    return [change_char(c) for c in text if change_char(c) is not None]


def add_spaces_evenly(char_list, n=5):
    counter = 0
    for i in xrange(n, len(char_list), n):
        char_list.insert(i + counter, ' ')
        counter += 1
    return char_list
