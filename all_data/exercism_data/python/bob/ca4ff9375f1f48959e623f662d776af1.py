__author__ = 'peersp'


def hey(statement):
    if not statement.strip():
        return 'Fine. Be that way!'
    elif statement.isupper():
        return 'Woah, chill out!'
    elif statement[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
