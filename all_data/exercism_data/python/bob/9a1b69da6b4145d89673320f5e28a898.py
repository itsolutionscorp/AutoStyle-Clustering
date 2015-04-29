#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """Respond to chatter based on simple rules."""
    
    # Nothing: All whitespace characters?
    what = what.strip()
    if not what:
        return "Fine. Be that way!"
    
    # Yelling: Are all letters uppercase?
    if what.isupper():
        return "Whoa, chill out!"
    
    # Question: Ends with '?'?
    if what.endswith('?'):
        return "Sure."
    
    # Anything else
    return "Whatever."
