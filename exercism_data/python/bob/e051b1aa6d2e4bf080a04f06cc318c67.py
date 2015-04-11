import re

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    answer = ''

    #
    # Had the Question section as the first section since it was the first
    # question/reply mentioned in the README. But one of the test cases 
    # needs Yelling section to take precedence. Moving it to start.
    #
    # YELLING: All strings in CAPS
    if what.isupper():
        answer = 'Whoa, chill out!'

    # Question: Ends with ?
    elif re.search('\?$', what) is not None:
        answer = 'Sure.'

    #
    # First attempt: Had checking just for an empty string and then just
    # a newline as good. But considering all the testcases that failed
    # for this, had to include all whitespaces as well
    #
    # Empty: Only whitespaces
    elif re.search('^\s*$', what):
        answer = 'Fine. Be that way!'

    # Whatever
    else:
        answer = 'Whatever.'

    return answer
