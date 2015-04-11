#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    is_yelling = False

    has_alpha = False
    statement = what.strip()

    if statement == '':
        return 'Fine. Be that way!'

    for c in statement:
        if not has_alpha and c.isalpha():
            has_alpha = True
            is_yelling = True

        if c.isalpha() and c.islower():
            is_yelling = False

    if is_yelling:
        return 'Whoa, chill out!'

    if statement[-1] == '?':
        return 'Sure.'
    
    return 'Whatever.'
