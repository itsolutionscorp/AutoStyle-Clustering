#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    return_value = ""
    if(not what.strip()):
        return_value = "Fine. Be that way!"
    elif(what.isupper()):
        return_value = "Whoa, chill out!"    
    elif(what.strip().endswith("?")):
        return_value = "Sure."
    else:
        return_value = "Whatever."
    return return_value
