#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    # Strip out non-alpha characters to test for yelling
    import string
    stripped = ''.join(ch for ch in what if ch.isalpha())
    if stripped != '':
        if stripped == stripped.upper():
            # Yelling
            return 'Whoa, chill out!'
    # Test for 'Addressed without saying anything'
    if all(x in string.whitespace for x in what):
        return 'Fine. Be that way!'
    # Test for question
    if what[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
