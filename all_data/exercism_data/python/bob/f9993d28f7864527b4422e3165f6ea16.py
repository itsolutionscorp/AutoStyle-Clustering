def hey(phrase):
    if phrase.isupper():
        return 'Woah, chill out!'

    if phrase.endswith('?'):
        return 'Sure.'

    if phrase.strip() == '':
        return 'Fine. Be that way!'

    return 'Whatever.'
