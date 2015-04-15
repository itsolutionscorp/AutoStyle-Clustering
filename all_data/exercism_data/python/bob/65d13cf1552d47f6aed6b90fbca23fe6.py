#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
    # Bob answers 'Sure.' if you ask him a question.
    # answers 'Whoa, chill out!' if you yell at him.
    # says 'Fine. Be that way!' if you address him without actually 
    # saying anything
    # 'Whatever.' to anything else
    
    # Remove whitespace in string
    what = what.replace(" ", "")
    #print "%s %r" % (what, what)

    if re.match(r'[ \t\n\r\f\v]', what) or what == '':
        # if the input is just special characters or no input at all
        response = 'Fine. Be that way!'
    elif what == what.upper():
        # Ignore numbers
        temp_what = re.sub(r'[!?,\' ]+', '', what)

        # A better way to check if the input is just numbers?
        try:
            float(temp_what)
            response = 'Whatever.'
            # Checking for #? case. Has to be a better way of doing this
            if what.endswith("?"):
                response = 'Sure.'
        except ValueError:
            # Actually yelling
            response = 'Whoa, chill out!'
    elif what.endswith("?"):
        # Given a question
        response = 'Sure.'
    else:
        # Unknown input
        response = 'Whatever.'

    return response
