"""
Script to give Bob's responses
-----------------------------------
Bob answers 'Sure.' if you ask him a question.
Bob answers 'Whoa, chill out!' if you yell at him.
Bob answers 'Fine. Be that way!' if you address him without actually saying
anything.
He answers 'Whatever.' to anything else.
"""

# 4 tests for Bob's responses
def hey(what):
    test = what.strip()
    result = ''

    # empty string result
    if test == '':
        result = "Fine. Be that way!"

    # yelling result (All caps and not all punctuation/numbers)
    elif test == test.upper() and test.strip('1234567890!?,. ') != '':
            result = "Whoa, chill out!"

    # question result (ends with question mark)
    elif test.endswith('?') == True:
        result = "Sure."

    # everything else result
    else:
        result = "Whatever."

    return result
