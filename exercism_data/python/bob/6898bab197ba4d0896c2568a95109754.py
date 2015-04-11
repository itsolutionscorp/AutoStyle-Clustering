#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """
    if question return answer "Sure."
    if yelling return answer "Whoa, chill out!"
    if empty string return "Fine. Be that way!"
    anything else return "Whatever."
    
    to make the forceful question edge case work i simply give priority to the uppercase check,
    """
    
    if what.isupper():
        return "Whoa, chill out!"
    elif what.strip().endswith('?'):
        return "Sure."
    elif what.strip() == '':
        return "Fine. Be that way!"
    else:
        return "Whatever."
