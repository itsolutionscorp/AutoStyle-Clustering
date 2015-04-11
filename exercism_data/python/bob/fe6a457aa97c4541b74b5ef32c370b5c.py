def hey(talk):
    if talk.strip() == '':
        return 'Fine. Be that way!'
    if talk.isupper():
        return 'Whoa, chill out!'
    if talk.endswith('?'):
        return 'Sure.'
    return'Whatever.'
