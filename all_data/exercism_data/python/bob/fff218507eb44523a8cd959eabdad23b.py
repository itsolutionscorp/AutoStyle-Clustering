#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    if what.strip():
        if what.isupper():
            response = "Whoa, chill out!"
            return response
        elif what[len(what) - 1] == '?':
            response = "Sure."
            return response
        else:
            response = "Whatever."
            return response
    else: 
        response = "Fine. Be that way!"
        return response
