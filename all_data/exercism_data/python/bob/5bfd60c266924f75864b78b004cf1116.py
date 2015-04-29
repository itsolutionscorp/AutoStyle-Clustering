#
# Patrick Daly
# Oct 10, 2014
# Exercism - bob
# 

def hey(what):
    what = what.strip()
    if what.endswith('?') and not what.isupper():
        answer = 'Sure.'
    elif what.isupper():
        answer = 'Whoa, chill out!'
    elif what == '':
        answer = 'Fine. Be that way!'
    else:
        answer = 'Whatever.'
    return answer
