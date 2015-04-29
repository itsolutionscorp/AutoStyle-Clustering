
def hey(x):
    if x.isupper() :
        return 'Whoa, chill out!'
    if x.endswith('?'):
        return 'Sure.'
    if x.strip() == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
