import re

def hey(comment):
    comment = comment.strip(' \n\t')

    if not comment:
        return 'Fine. Be that way!'

    if comment == comment.upper() and re.search('[A-Z]', comment) is not None:
        return 'Whoa, chill out!'

    if comment[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
