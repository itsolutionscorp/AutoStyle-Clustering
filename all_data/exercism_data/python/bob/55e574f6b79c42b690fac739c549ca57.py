def hey(phrase):
    if len(phrase.strip()) == 0:
        return 'Fine. Be that way!'
    elif phrase.isupper():
        return 'Whoa, chill out!'
    elif phrase.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'        