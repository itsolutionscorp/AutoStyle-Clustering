#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # Yelling is when all alpha chars are uppercase, return 'Whoa, chill out!'
    # Question ends with question mark, return 'Sure.'
    # Silence is only whitespace, retrun 'Fine. Be that way!'
    # Default return 'Whatever.'
    prompt = what.strip()  # remove leading and trailing whitespace
    if prompt.isupper():
        return 'Whoa, chill out!'
    if prompt.endswith('?'):
        return 'Sure.'
    if len(prompt) == 0:  # whitespace has been stripped
        return 'Fine. Be that way!'
    return 'Whatever.'
