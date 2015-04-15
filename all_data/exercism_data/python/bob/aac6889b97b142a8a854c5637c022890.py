#
# Skeleton file for the Python "Bob" exercise.

#
def hey(what):
    answer = 'Whoa, chill out!'

    if what.strip()[-1:] == '?':
        if what[-2].isdigit() or not what == what.upper():
            answer = 'Sure.'
    elif what == what.upper() and any(c.isalpha() for c in what) and not what.replace(" ","") == '':
        answer = 'Whoa, chill out!'
    elif what.strip() == '':
        answer = 'Fine. Be that way!'
    else:
        answer = 'Whatever.'

    return answer
