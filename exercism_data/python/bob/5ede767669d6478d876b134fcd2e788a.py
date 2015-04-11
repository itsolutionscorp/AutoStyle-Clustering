#
# captainnurple's code for the Python "Bob" exercise.
#
def hey(what):
    # Start by cleaning leading and trailing whitespace
    what = what.strip()

    # Now process for response.
    # First test for empty string. If it's empty we're done
    if not what: # empty strings are 'falsy'
        return 'Fine. Be that way!'
    # Next test for yelling. All letters should be CAPS and ignore numbers. Yelling takes precedence over questions
    elif what.isupper():
        return 'Whoa, chill out!'
    # Next test for questions
    elif what.endswith('?'): 
        return 'Sure.'
    # Anything else is meh
    else:
        return 'Whatever.'
