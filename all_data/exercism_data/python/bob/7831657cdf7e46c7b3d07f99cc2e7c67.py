def hey(phrase):

    if nothing_said(phrase):
        return "Fine. Be that way!"
    if is_yell(phrase):
        return "Woah, chill out!"
    elif is_question(phrase):
        return "Sure."
    else: return "Whatever."


#check to see if any characters were entered aside from
#spaces, null characters, or escape characters.
def nothing_said(phrase):
    flag = True
    i = 0
    while (flag) & (i < len(phrase)-1):
        if (phrase[i:i+1] == "\."):
            i = i+2
        else :
            flag = (phrase[i] == ' ')
            i += 1
    return flag

#check to see if the phrase entered is in all caps
def in_caps(phrase):
    return phrase == phrase.upper() and (phrase.upper() != phrase.lower())

# check to see if phrase entered is a yell
def is_yell(phrase):
    return in_caps(phrase)

#check to see if phrase entered is a question
def is_question(phrase):
    return phrase[-1] == '?'
