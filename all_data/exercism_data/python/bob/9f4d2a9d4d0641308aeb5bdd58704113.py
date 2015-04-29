def hey(txt):
    if txt.upper() == txt and not txt.lower() == txt:
        return 'Whoa, chill out!'
    elif txt.endswith('?'):
        return 'Sure.'
    elif len(txt.strip()) == 0:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
