#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.replace(' ', '').replace('\t', '').replace('\n','')
    if what.isupper() :
        return 'Whoa, chill out!'
    elif what[-1:] == '?' :
        return 'Sure.'
    elif what == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
