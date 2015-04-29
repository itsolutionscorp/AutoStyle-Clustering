# -*- coding: utf-8 -*-

# Assignment of Bob's responses to string variables
question_resp = "Sure."
yell_resp = "Whoa, chill out!"
nothing_resp = "Fine. Be that way!"
otherwise_resp = "Whatever."


# Main function that processes input from Bob's interlocutor
# Uses an if iterator to evaluate input into 1 of the 4 classifications
def hey(a_string):
    clean_string = string_cleanse(a_string)
    if is_yelling(clean_string) is True:
        return yell_resp
    elif is_question(clean_string) is True:
        return question_resp
    elif says_nothing(clean_string) is True:
        return nothing_resp
    else:
        return otherwise_resp


# Tweaks the input into a format acceptable to pass onto hey()
# Removes leading and trailing whitespace and escape characters eg \t
def string_cleanse(a_string):
    clean_string = a_string.replace("\\", "").strip()
    return clean_string


# Does the input have a trailing '?'? (After ws removal)
def is_question(a_string):
    if a_string != "":
        if a_string[len(a_string)-1] == "?":
            return True
        else:
            return False


# If input has any lower case, it's not yelling
def is_yelling(a_string):
    x = None
    for i in a_string:
        if i.isalpha() is True:
            if i != i.upper():
                x = False
                break
            else:
                x = True
    return x


# If input has no characters
def says_nothing(a_string):
    if a_string == "":
        return True
    else:
        return False
