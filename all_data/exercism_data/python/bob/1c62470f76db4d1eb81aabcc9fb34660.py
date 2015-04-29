#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if (what == None or what == '' or what.isspace()):
        return 'Fine. Be that way!'
    elif what.isupper() or (what.endswith("?") & what.isupper()):
        return 'Whoa, chill out!'
    elif what.strip().endswith("?"):
        return 'Sure.'
    else:
        return 'Whatever.'
