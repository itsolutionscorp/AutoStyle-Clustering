""" Hi there! I'm checking out Exercism,
and poking at Python!
I encourage all nits and brutal feedback
My goals were a clean, simple, direct implementation
and pep8 formatting <- ding me on this please!
http://www.python.org/dev/peps/pep-0008/
I may refactor this to avoid the if statements
I have experience in other languages, so that may
leak through!
"""

# bob responses to particular types of phrases
YELLING_RESPONSE   = 'Woah, chill out!'
QUESTION_RESPONSE  = 'Sure.'
NOTHING_RESPONSE   = 'Fine. Be that way!'
ALL_OTHER_RESPONSE = 'Whatever.' # default response


# class handling 'Bob' phrases
class Bob:
    # redirected because this has no
    #  reason to be an instance method
    #  structure forced by tests
    def hey(self, phrase):
        return hey(phrase)


# processes a 'Bob' phrase, giving the appropriate response
def hey(phrase):

    # note yelling has precedence over other responses
    if is_yelling(phrase): 
        return YELLING_RESPONSE

    if is_question(phrase):
        return QUESTION_RESPONSE

    if is_nothing(phrase):
        return NOTHING_RESPONSE

    return ALL_OTHER_RESPONSE # default response


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
