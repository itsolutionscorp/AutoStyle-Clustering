'''
Bob talks back.

Author: Luke Shillabeer (lshillabeer@gmail.com)

This program implements the logic defined by the first problem (BOB) in the 
exercism.io python problem-set.

TESTING:
    
    bob_test.py (seperate file) will run numerous test-cases over the 
    Bob module.

REVISION HISTORY:

    24/09/14: Implemented initial version of the hey function.
    25/09/14: Wrote documentation to describe functionality of Bob module
'''

def hey(strInput):
    '''
    DESCRIPTION:
    Return a string in responce to a variety of string inputs; string returned
    is based upon the ruleset below;

        - Bob answers 'Sure.' if you ask him a question.
        - He answers 'Woah, chill out!' if you yell at him.
        - He says 'Fine. Be that way!' if you address him without actually 
          saying anything.
        - He answers 'Whatever.' to anything else.

    ASSUMPTIONS:
    This solution assumes input will always be in string format; if input is 
    non-interable this code will raise an exception.

    It is assumed that the only characters that represent a lack of speech are
    a space, a tab and a newline character.

    Questions are assumed to ALWAYS finish with a question-mark and are not
    allowed to be all upper-case (covered by yelling instead).

    Yelling is assumed to either be all upper-case OR end with an exclaimation
    mark.

    ARGS:
    strInput: a string input regarded as "speech" to be responded to by this
              function.

    RETURN:
    This function returns one of a variety of string literals, determined by
    the ruleset and assumptions above.

    EXAMPLES:
    >>> hey('WHAT THE HELL WERE YOU THINKING?')
    'Woah, chill out!'
    >>> hey('Ending with ? means a question.')
    'Whatever.'
    >>> hey('Wait! Hang on. Are you going to be OK?')
    'Sure.'
    >>> hey('    ')
    'Fine. Be that way!'
    '''

    # determine whether string is comprised entirely of spaces, tabs and 
    # newlines, which all represent a lack of speech and therefore should 
    # return the "Fine. Be that way!" text.
    SPACERS = " \t\n"
    all_spacers = True
    for letter in strInput:
        if letter not in SPACERS:
            all_spacers = False
            break   

    # the order of these if-elif's matters, due to the logic of what a 
    # question vs. yelling are (changing it WILL break test-cases).
    if not strInput or all_spacers == True:
        return "Fine. Be that way!"
    elif strInput[-1] == "?" and strInput.isupper() == False:
        return "Sure."
    elif strInput.isupper():
        return "Woah, chill out!"
    else:
        return "Whatever."
