from re import search


def hey(what):
    content = what.strip()
    has_text = bool(search(r'[a-zA-z]', content))
    yelling = has_text and content == content.upper()

    if yelling:
        return 'Whoa, chill out!'
    elif content.endswith('?'):
        return 'Sure.'
    elif content == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
