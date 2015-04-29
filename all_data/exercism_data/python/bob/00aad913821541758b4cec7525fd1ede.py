from string import upper, lower

__author__ = "Anthony Rodriguez"

def hey(user_input):
    # remove excess whitespace
    clean_user_input = user_input.strip(' \t\n\r')

    # check if empty
    if clean_user_input:
        # if ends with '?' and is neither lower or upper; meaning it is a number; this defines a question
        if clean_user_input[-1] == '?' and ( clean_user_input != upper(clean_user_input) or clean_user_input == lower(clean_user_input)):
            return 'Sure.'
        # if it is upper case and changes when lowered; defines yelling
        if clean_user_input == upper(clean_user_input) and clean_user_input != lower(clean_user_input):
            return 'Whoa, chill out!'

        # anything else
        return 'Whatever.'
    else:
        # without saying anything; blank entry
        return 'Fine. Be that way!'
