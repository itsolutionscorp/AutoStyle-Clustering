def is_valid_uppercase_phrase(s):
    return s.isupper() and any(c.isalpha() for c in s)


def hey(phrase):
    if phrase.endswith('?'):
        if is_valid_uppercase_phrase(phrase):
            return 'Woah, chill out!'
        else:
            return 'Sure.'
    elif len(phrase) and is_valid_uppercase_phrase(phrase):
        return 'Woah, chill out!'
    elif '' == ''.join(phrase.split()):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'


if __name__ == '__main__':
    print hey('Bob!')
