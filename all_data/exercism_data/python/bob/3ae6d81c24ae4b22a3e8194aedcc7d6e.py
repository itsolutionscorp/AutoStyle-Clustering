#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if what == "":
        return "Fine. Be that way!"
    elif what.upper() == what and alpha_exist_in_string(what):
       return "Whoa, chill out!" 
    elif what[-1] == "?":
        return "Sure."
    else:
        return "Whatever."

def alpha_exist_in_string(str):
    for letter in str:
        if letter.isalpha() is True:
            return True
    return False
