#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if getLastChar(what)==-1:
        return "Fine. Be that way!"

    if what.isupper():
        return "Whoa, chill out!"

    if getLastChar(what)=="?":
        return "Sure."

    return "Whatever."
   


def getLastChar(string):

    if len(string)==0:
        return -1

    i=len(string)-1
    while i>-1 and string[i]==" ":
        i=i-1

    if string[i]==" ":
        return -1

    return string[i]
   

def containsLowerCase(string):
    for i in string:
        if ord(i)>96 and ord(i)<123:
            return true

    return false
