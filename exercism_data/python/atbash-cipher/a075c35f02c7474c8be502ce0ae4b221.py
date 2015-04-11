def encode(string):
    return_string = ''
    total_chars = 0
    max_chars = sum(1 for c in string if c.isalnum())
    for s in string:
        if s.isalnum():
            if s.isalpha():
                s = s.lower()
                return_string += chr(ord(s) + 27 - 2 * (ord(s)-96))
            if s.isdigit():
                return_string += s
            total_chars += 1
            if not total_chars % 5 and not total_chars == max_chars:
                return_string += ' '
    return return_string


def decode(string):
    return_string = ''
    for s in string:
        if s.isalnum():
            if s.isalpha():
                return_string += chr(ord(s) + 27 - 2 * (ord(s)-96))
            if s.isdigit():
                return_string += s
    return return_string
