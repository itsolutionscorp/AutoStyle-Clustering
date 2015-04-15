def hey(phrase):
    if phrase.isupper():
        return 'Whoa, chill out!'
    elif phrase.endswith('?'):
        return 'Sure.'
    elif not phrase.strip():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
