#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    response = "Whatever."
    what = "".join(what.split())
    print(what)
    if what == "":
        response = "Fine. Be that way!"
    else:
        last = what[-1]
        print(last)
        if last == "?":
            response = "Sure."
        if what.isupper():
            response = "Whoa, chill out!"
        
        
    return response
