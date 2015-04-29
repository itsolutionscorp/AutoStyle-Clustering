#
# Skeleton file for the Python "Bob" exercise.
#
def isShout(what) :
    boolean = False
    for i in what :
        if i >= 'a' and i <= 'z' :
            boolean = False
            break
        elif i >= 'A' and i <= 'Z' : boolean = True
    return boolean

def isQuestion(what) :
    boolean = False
    for i in what :
        if i == '?' : boolean = True
        elif (i >= 'a' and i <= 'z') or (i >= 'A' and i <= 'Z') : boolean = False
    return boolean

def isSillent(what) :
    boolean = True
    for i in what :
        if i != ' ' and  i != '\t' :
            boolean = False
            break
    return boolean

def hey(what) :
    if isShout(what) : return "Whoa, chill out!"
    elif isQuestion(what) : return "Sure."
    elif isSillent(what) : return "Fine. Be that way!"
    else : return "Whatever."
