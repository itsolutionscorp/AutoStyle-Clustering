"""
temp docstring.. need to abide by pylint! :)
"""

def hey(arg):
    """
    just needed to define rules as to when a particular response
    is triggered
    """
    if not arg.strip():
        return 'Fine. Be that way!'
    elif arg.isupper():
    	return 'Woah, chill out!'
    elif arg[-1:] == '?':
        return 'Sure.'
    return 'Whatever.'
