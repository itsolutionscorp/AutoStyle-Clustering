# Bridgette Eichelberger
# 9/23/2014
# hey.py

def hey(arg):
    if arg.isspace() or arg == '':
        return "Fine. Be that way!"

    foundLower = False
    foundUpper = False
    for l in arg:
        if l.islower(): foundLower = True
        if l.isupper(): foundUpper = True

    # uppercase only means shouting    
    if foundUpper and not foundLower:
        return "Whoa, chill out!"

    else:
        # numers and lowercase ending with ? means question,
        if arg[len(arg)-1] == "?":
            return "Sure."

        # while ending with . or no punctuation is a statement
        else:
            return "Whatever."




