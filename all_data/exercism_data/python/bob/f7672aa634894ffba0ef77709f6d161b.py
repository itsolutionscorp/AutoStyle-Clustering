##BOB MODULE
#Submitted a third time cause I forgot to take out an old comment

def hey(string_in):
    if not string_in.strip(): #silence
        return "Fine. Be that way!"
    elif string_in.isupper(): #shouting
        return "Whoa, chill out!"
    elif string_in[-1] == "?": #question
        return "Sure."
    else: #anything else
        return "Whatever."
