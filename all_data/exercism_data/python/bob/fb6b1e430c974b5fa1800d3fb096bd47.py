#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    # List of the possible responses
    responses = ['Whatever.','Sure.',
             'Whoa, chill out!',
             'Fine. Be that way!']

    # Take out all leading and trailing spaces/non-printing chars
    what1 = what.strip()

    # Default response is 'Whatever.' 
    index = 0

    # If empty line, then 'Fine. Be that way!'
    if what1 == '':
        index = 3
    else:
        # Test for shouting (ALL CAPS), 'Whoa, chill out!'
        if testAllCaps(what1):
            index = 2
        else:
            # If not shouting, then question mark at end.
            # Response is 'Sure.'
            if what1[-1] == '?':
                index = 1
        
    return responses[index]

def testAllCaps(txt):

    # Basic idea of routine is to look for absence of lower case
    # characters and at least one upper case character.
    # Characters, such as numbers and symbols which do not
    # have upper and lower cases, do not affect the outcome
    
    contains1Up = 0
    contains1Low = 0

    # Assumption is not all caps
    allCaps = 0
    
    for charTest in txt:
        # Test if the character has upper and lower cases
        if charTest.upper() != charTest.lower():
            # Test if character is lower.  One lower means
            # not all caps and can break out of the loop
            if charTest == charTest.lower():
                contains1Low = 1
                break
            # Test if upper case.  Do not break and keep
            # searching the string for lower case characters.
            elif charTest == charTest.upper():
                contains1Up = 1

    if not contains1Low:
        if contains1Up:
            allCaps = 1

    return allCaps
        




# **********************************************************
# ** Remove following code. Included to show evolution     
# ** of all caps algorithm
# **********************************************************

def testAllCaps_orig(txt):
    # Test all characters for lowercase letters.
    #    Test extended characters (ord 192 - 400)
    #      First test if they differentiate upper and lower case
    #      Second test if the current character is not upper (lower)
    # If no lowercase letters, test for at least one uppercase.
    # If at least one upper case letter or extended (umlauts) then all caps
    # All numbers and non-letters are neutral.
    
    capLetterLower = 65
    capLetterUpper = 90

    lowLetterLower = 97
    lowLetterUpper = 122

    extLetterLower=192
    extLetterUpper=400
    
    contains1Up = 0
    contains1Low = 0

    allCaps = 0
    
    for charTest in txt:
        if ord(charTest) >= lowLetterLower and ord(charTest) <= lowLetterUpper:
            contains1Low = 1
            break
        if ord(charTest) >=192 and ord(charTest) <= 400:
            if charTest.lower() != charTest.upper() and charTest != charTest.upper():
                contains1Low = 1
                break
    
    if contains1Low == 0:
        for charTest in txt:
            if ord(charTest) >= capLetterLower and ord(charTest) <= capLetterUpper:
                contains1Up = 1
                break
            if ord(charTest) >=192 and ord(charTest) <= 400:
                if charTest == charTest.upper():
                    contains1Up = 1
                    break

            
    if contains1Low == 0 and contains1Up == 1:
        allCaps = 1

    return allCaps
