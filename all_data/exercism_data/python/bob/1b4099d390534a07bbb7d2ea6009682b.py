import re


def hey(arg):
    if arg.strip()=='':
        return ('Fine. Be that way!')
    if re.search('[a-z]', arg, re.IGNORECASE):
        if arg.upper() == arg: 
                return ('Whoa, chill out!')
    if arg.endswith('?'):
        return ('Sure.')
    return ('Whatever.')
