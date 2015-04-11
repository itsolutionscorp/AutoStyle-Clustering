#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # First remove all spaces in the string, as they are not necessary
    what_string = what.replace(" ", "")
    return_string = ""
    letter_list = ("a","b","c","d","e","f","g","h","i","j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
    # before we begin, we need to determine if the string contains letters
    # If there are no letters, then the test for yelling will always be true
    no_letters = True
    what_length = len(what_string)
    x = 0
    while no_letters:
        if what_length == 0: break
        if what_string[x].lower() in letter_list:
            no_letters = False
        if x == what_length - 1:
            break
        x += 1

    # From here, we can begin testing the various strings
    # First, test if Bob is screamed at. This test is needed 
    # becasue the yelled phrase could be in the form of a quesiton
    # If you're not asked a qustion, maybe you're yelled at
    # This test will be all capital letters
    if what_string == what_string.upper() and not no_letters:
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
