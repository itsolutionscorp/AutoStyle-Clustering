#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if 0 == len(what):
        return 'Fine. Be that way!'
    elif any(map(str.isupper, what)) and not any(map(str.islower, what)):
        return 'Whoa, chill out!'
    elif '?' == what[-1]:
        return 'Sure.'

    return "Whatever."

