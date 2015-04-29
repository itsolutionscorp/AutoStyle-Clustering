#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if check_silence(what):      #detect silence
        return "Fine. Be that way!"
    x = -1                  #for checking whitespaces at the end
    while what[x] == ' ':   #check for whitespaces
        x -= 1
    if check_shout(what):   #check for shouting
        return "Whoa, chill out!"
    elif what[x] == "?":    #check for questionmarks at the end of sentence
        return "Sure."
    else:                   #other cases
        return "Whatever."


def check_shout(what):
    flag = False            #check if there are any letters
    for x in what:
        if x.isalpha():
            flag = True
        if not x.isupper() and x.isalpha():
            return False
    if flag:
        return True
    else:
        return False

def check_silence(what):
    if len(what)==0:
        return True
    for x in what:
        if not x.isspace():
            return False
    return True
