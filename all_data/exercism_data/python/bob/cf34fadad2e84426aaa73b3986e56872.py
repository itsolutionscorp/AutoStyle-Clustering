# -*- coding: utf-8 -*-

# Assignment of Bob's responses to string variables
question_resp = "Sure."
yell_resp = "Whoa, chill out!"
nothing_resp = "Fine. Be that way!"
otherwise_resp = "Whatever."


# Process input from Bob's interlocutor and provide a determined response
def hey(a_string):
    clean_string = a_string.replace("\\", "").strip()
    if is_yelling(clean_string):
        return yell_resp
    if (clean_string != "") and (clean_string[-1] == "?"):
        return question_resp
    elif clean_string == "":
        return nothing_resp
    else:
        return otherwise_resp


# If input has any lower case, it's not yelling.
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
