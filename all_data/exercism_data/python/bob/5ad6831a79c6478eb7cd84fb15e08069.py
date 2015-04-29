__author__ = 'linda'


def hey(statement):
    assert isinstance(statement, str)
    if statement.endswith('?') and not statement.isupper():
        return 'Sure.'
    elif statement.isupper():
        return 'Whoa, chill out!'
    elif statement.isspace() or statement == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
