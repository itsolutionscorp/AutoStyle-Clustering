#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # define variables
    alphabet = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]
    areletters = 0

    # check for files
    for letter in alphabet:
        if what.find(letter) != -1 or what.find(letter.upper()) != -1:
            areletters = 1

    # remove \t and replace with spaces
    what = what.replace("\t", "   ")

    # check for attributes
    if areletters == 1:
        if what.replace(" ","") == "":
            answer = "Fine. Be that way!"
        elif what.upper() == what:
            answer = "Whoa, chill out!"
        elif what[len(what) - 1] == "?":
            answer = "Sure."
        else:
            answer = "Whatever."
    else:
        if what.replace(" ","") == "":
            answer = "Fine. Be that way!"
        elif what[len(what) - 1] == "?":
            answer = "Sure."
        else:
            answer = "Whatever."

    # return Bob's answer
    return answer
