#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    last=len(what)-1
    if what.find("\"") !=-1:
        spec=what.find("\"")
        what=what.replace(what[spec:spec+2],"") 
    if what.strip()=="":
        return "Fine. Be that way!"      
    if what.isupper():
        return "Whoa, chill out!"
    if what[last]=="?":
        return "Sure."
    else:
        return "Whatever."
    return what
