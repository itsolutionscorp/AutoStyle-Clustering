def return_response(statement):
    if not statement or statement.isspace():
        return "Fine. Be that way!"
    if statement.isupper():
        return "Whoa, chill out!"
    if statement[-1:] == "?":
        return "Sure."   
    return "Whatever."

def hey(statement):
    return return_response(statement)
