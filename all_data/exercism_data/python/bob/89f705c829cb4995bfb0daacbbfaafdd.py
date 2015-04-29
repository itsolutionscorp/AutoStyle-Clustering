#
# Skeleton file for the Python "Bob" exercise.

def hey(answer):

    # Get rid of all the leading and trailing white space in a string
    answer = answer.strip()
    
    # Answer if string is in all UPPERCASE letters
    if answer.isupper():
        return "Whoa, chill out!"
    
    # Answer if string ends with a question mark        
    if answer.endswith("?"):
        return "Sure."

    # Answer if the string does not contain text
    if answer.isspace() or len(answer)==0:
        return "Fine. Be that way!"
    
    # Answer for anything else in the string that is not covered above
    return "Whatever."
