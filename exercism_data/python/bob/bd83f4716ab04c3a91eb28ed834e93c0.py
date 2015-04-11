#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if len(what.strip()) == 0:
        return 'Fine. Be that way!'
    elif what.strip().upper() == what.strip() and what.strip().upper() != what.strip().lower():
            return 'Whoa, chill out!'
    elif what.strip()[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'

    return
