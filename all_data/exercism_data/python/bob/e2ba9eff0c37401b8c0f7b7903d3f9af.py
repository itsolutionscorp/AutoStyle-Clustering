""" Hi there! I'm checking out Exercism,
and poking at Python!
I encourage all nits and brutal feedback
My goals were a clean, simple, direct implementation
and pep8 formatting <- ding me on this please!
Version 2 uses more advanced functionality
in the form of list comprehesions. The goal was to
remove all if/loop structures.
http://www.python.org/dev/peps/pep-0008/
"""

# note the RESPONSE_MAP below the functions
#  is important context. it must be below
#  due to precedence issues

# class handling 'Bob' phrases
class Bob(object):
    # redirected because this has no
    #  reason to be an instance method
    #  structure forced by tests
    def hey(self, phrase):
        return hey(phrase)


# processes a 'Bob' phrase, giving the appropriate response
def hey(phrase):
    valid_responses = [response for (test,response) in RESPONSE_MAP if test(phrase)]
    first_valid_response = valid_responses[0]
    return first_valid_response


# determines if a particular phrase counts as a question
def is_question(phrase):
    # questions end with question marks
    return phrase.endswith("?")


# determines if a particular phrase is 'yelling'
# yelling means all caps
#   (and has characters that change case)
def is_yelling(phrase):
    return (phrase == phrase.upper() # all upper case 
        and phrase.lower() != phrase.upper() # and can change case
        ) 


# determines if a phrase has no content
#  meaning empty or only whitespace
def is_nothing(phrase):
    # note: relies on 'empty strings are false' per pep8
    # http://www.python.org/dev/peps/pep-0008/
    # "For sequences, (strings, lists, tuples), 
    #   use the fact that empty sequences are false."
    return not phrase.strip() # strip whitespace and test for empty

# matches ANY phrase
def is_anything_else(phrase):
    return True

# note this is below functions due to precedence issues
# note order matters and is intentional
RESPONSE_MAP = [
    (is_yelling, 'Woah, chill out!'),
    (is_question, 'Sure.'),
    (is_nothing, 'Fine. Be that way!'),
    (is_anything_else, 'Whatever.')
]
