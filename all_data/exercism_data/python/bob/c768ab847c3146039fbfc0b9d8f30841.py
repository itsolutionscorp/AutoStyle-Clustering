#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if (what.endswith('?') and not what.isupper()):
        # Is a question not yelled
        return 'Sure.'
    if what.isupper():
        # Is yelled
        return 'Whoa, chill out!'
    if (what.isspace() or what == ''):
        # Is nothing said 
        return 'Fine. Be that way!'
    # The default answer to verything else!
    return 'Whatever.'
