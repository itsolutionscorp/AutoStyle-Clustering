#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # First remove all spaces in the string, as they are not necessary
    what_string = what.strip()
    return_string = ""
    what_length = len(what_string)
    
    # From here, we can begin testing the various strings
    # First, test if Bob is screamed at. This test is needed 
    # becasue the yelled phrase could be in the form of a quesiton
    # If you're not asked a qustion, maybe you're yelled at
    # This test will be all capital letters
    if what_string.isupper():
        return_string = 'Whoa, chill out!'
    # Next, perhaps they didn't actually say anything
    elif what_string == "":
        return_string = 'Fine. Be that way!'
    # Next test for a question. As the strings are in English, any question
    # # will end with a qustion mark
    elif what_string[what_length - 1] == "?":
        return_string = 'Sure.'
    # Lastly, anything else should return 'Whatever'
    else:
        return_string = 'Whatever.'

    return return_string
