#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    isShout = True
    isSillent = True
    isQuestion = True
    isWhatever = True
    for i in what :
        if i == '?' : isQuestion = True # if string ends with ?, it is Question
        else if i >= 'a' && i <= 'z' : # if string contains small letters, it cannot be Shout and Sillent
            isShout = False
            isSillent = False
            isQuestion = False
        else if i >= 'A' && i <= 'Z' : # if string contains capital letters, it cannot be Sillent.
            isSillent = False
            isQuestion = False
        else if i != ' ' : isSillent = False # if string contains special letters, it cannon be Sillent.
    if isSillent : return "Fine. Be that way!"
    else if isShout : return "Whoa, chill out!"
    else if isQuestion : return "Sure."
    else if isWhatever : return "Whatever."
