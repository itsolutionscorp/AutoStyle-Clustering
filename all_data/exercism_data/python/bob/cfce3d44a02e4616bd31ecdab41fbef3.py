class Bob(object):
    def hey(_, inp):
        if inp is None:
            return 'Fine. Be that way!'
        if inp.strip() == '':
            return 'Fine. Be that way!'
        if inp.isupper():
            return 'Woah, chill out!'
        if inp.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
