''' This module is for the Python "Bob" exercise in exercism.io.
    Trying my hand at a more pythonic way to answer the exercise after seeing
    some of the submitted answers.
    Lessons for this exercise: RTFM (or documentation),
    you may be over complicating things.
'''

def hey(what):
    ''' This function will take a unicode string and return a string depending
        on the input as specified in exercism.io python exercise 'bob'.
        If a question is asked, then 'Sure.' is returned.
        If you yell, then 'Whoa, chill out!' is returned.
        If you address the program without saying anything, 'Fine. Be that way!'
        is returned.
        And for everything else, 'Whatever.' is returned.
    '''

    input_str = what.strip()

    if input_str == "":
        return 'Fine. Be that way!'
    elif input_str.isupper():
        return 'Whoa, chill out!'
    elif input_str[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
