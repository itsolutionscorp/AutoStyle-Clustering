def hey(txt):
    txt=txt.strip()
    if txt.isupper():
        return 'Whoa, chill out!'
    elif txt.endswith('?'):
        return 'Sure.'
    elif len(txt) == 0:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
