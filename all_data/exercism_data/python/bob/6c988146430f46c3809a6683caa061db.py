def hey(s):
    if ''.join(s.strip().split()).isupper():
        return 'Whoa, chill out!'
    if s.strip().endswith('?'):
        return 'Sure.'
    if s.strip() == '':
        return 'Fine. Be that way!'
    return 'Whatever.'
