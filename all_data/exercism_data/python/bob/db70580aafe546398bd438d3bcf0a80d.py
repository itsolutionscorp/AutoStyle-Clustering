def hey(statement):
    if statement.isupper() == True:
        return "Whoa, chill out!" 
    elif statement.endswith('?'):
        return "Sure."
    elif statement.isspace() or statement == "":
        return "Fine. Be that way!"
    else:
        return "Whatever."
