def hey(statement):
    if statement.isupper():
        return "Woah, chill out!"
    elif statement.endswith('?'):
        return "Sure."
    elif statement.isspace() or not statement:
        return "Fine. Be that way!"
    else:
        return "Whatever."
