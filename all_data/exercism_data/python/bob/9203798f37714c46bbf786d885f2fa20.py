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
    testStr = clean_text(what)
    result = ''

    # empty string result
    if testStr == '':
        result = "Fine. Be that way!"
    # question result (ends with question mark and not yelling)
    elif testStr[len(testStr) - 1] == "?" and test_all_caps(testStr) == False:
        result = "Sure."
    # yelling result (all alphabetic characters are caps)
    elif test_all_caps(testStr) == True:
        result = "Whoa, chill out!"
    # everything else result
    else:
        result = "Whatever."

    return result

# removes whitespace, tabs and newlines
def clean_text(input):
    result = input
    result = result.replace(' ', '')
    result = result.replace('\n', '')
    result = result.replace('\t', '')

    return result

# returns true is all caps exist
def test_all_caps(input):
    result = True
    containsAlpha = False

    for c in input:
        # any alphabetic character makes containsAlpha == True
        # (does not change once made True)
        if c.isalpha() or containsAlpha == True:
            containsAlpha = True
        # any lowercase alphabetic character stops loop,
        # obviously not all caps
        if not c.istitle() and c.isalpha():
            result = False
            break

    # return for no alphabetic characters
    if containsAlpha == False:
        result = False

    return result
