def hey(statement):
    if statement.isupper():
        return "Whoa, chill out!" 
    elif statement.endswith('?'):
        return "Sure."
    elif not statement.strip() or statement == "":
        return "Fine. Be that way!"
    else:
        return "Whatever."