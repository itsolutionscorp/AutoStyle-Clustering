def hey(what):
    # Return 'whatever' in the case of a non-string input
    if not isinstance(what, basestring):
        return 'Whatever.'

    # Yelling, as defined by all cased characters being capitalized
    if what.isupper():
        return 'Whoa, chill out!'

    # Question, as defined by the last non-whitespace character being '?'
    if what.rstrip().endswith('?'):
        return 'Sure.'

    # Not saying anything, as defined by whitespace or empty string
    if what.isspace() or not what:
        return 'Fine. Be that way!'

    # All other queries
    return 'Whatever.'
