#
# Skeleton file for the Python "Bob" exercise.
#
from string import punctuation

def hey(what):
    '''
    Returns Bob's lackadasical response to input.

    @ How:
    First, we remove all trailing whitespace.
    We then check for the following, in order:
    * If what was said to Bob contained nothing.
    * If Bob is being yelled at.
    * If Bob is being asked a question.
    * If what was said only contains punctuation.

    @ Why:
    Checking for any response at all is done first
    because it is the least expensive operation &
    exclusive to all others.
    
    We check for yelling before questions so that if
    a question is yelled at Bob, he responds to the
    yelling first.

    We check if the response is nothing but
    punctuation absolutely last because it is the
    most expensive operation.
    '''

    # Remove all trailing whitespace
    what = what.rstrip()

    # If they address him without saying anything,
    # they must have given us an empty string.
    if what == "":
        return 'Fine. Be that way!'

    # If they're yelling,
    # all of their letters must be upper case
    elif what.isupper():
        return 'Whoa, chill out!'

    # If they're asking a question,
    # what must end in a question mark.
    elif what[-1] == "?":
        return 'Sure.'

    '''
    else:
        return 'Whatever.'
    '''

    # We check one last time to see if what
    # was said actually had any contents.
    only_punctuation = True
    for any_char in what:
        if any_char not in punctuation:
            only_punctuation = False

    # and if not,
    if only_punctuation:
        return 'Fine. Be that way!'

    return 'Whatever.'
    

