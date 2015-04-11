""" Hi there! I'm checking out Exercism,
and poking at Python!
I encourage all nits and brutal feedback
My goals were a clean, simple, direct implementation
and pep8 formatting <- ding me on this please!
Version 2 uses more advanced functionality
in the form of list comprehensions and generators.
The goal was to remove all if/loop structures.
http://www.python.org/dev/peps/pep-0008/
"""

# note the RESPONSE_MAP below the functions
#  is important context. it must be below
#  due to precedence issues


class Bob(object):
    """ class handling 'Bob' phrases
    """

    def hey(self, phrase):
        """redirected because this has no
           reason to be an instance method
           structure forced by tests
        """
        return hey(phrase)


def hey(phrase):
    """ processes a 'Bob' phrase, giving the appropriate response
    """
    # get the first response that passes the test for the phrase, in order
    return (response for (test,response) in RESPONSE_MAP if test(phrase)).next()



def is_question(phrase):
    """ determines if a particular phrase counts as a question
    """
    # questions end with question marks
    return phrase.endswith("?")


def is_yelling(phrase):
    """ determines if a particular phrase is 'yelling'
        yelling means all caps
        (and has characters that change case)
    """
    return (phrase == phrase.upper() # all upper case 
    and phrase.lower() != phrase.upper() # and can change case
    ) 


def is_nothing(phrase):
    """ determines if a phrase has no content
        meaning empty or only whitespace
    """
    # note: relies on 'empty strings are false' per pep8
    # http://www.python.org/dev/peps/pep-0008/
    # "For sequences, (strings, lists, tuples), 
    #   use the fact that empty sequences are false."
    return not phrase.strip() # strip whitespace and test for empty


def is_anything_else(phrase):
    """ matches ANY phrase
    """
    return True

""" note this is below function definitions due to 
        precedence issues
    note order matters and is intentional
"""
RESPONSE_RULES = [
    (is_yelling, 'Woah, chill out!'),
    (is_question, 'Sure.'),
    (is_nothing, 'Fine. Be that way!'),
    (is_anything_else, 'Whatever.')
]
