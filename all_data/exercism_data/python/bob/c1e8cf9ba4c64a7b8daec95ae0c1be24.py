def hey(statement):
    statement = statement.replace(" ", "")
    statement = statement.replace("\t", "")
    if statement == "":
        return "Fine. Be that way!"
    elif statement.isupper():
        return "Whoa, chill out!"
    elif statement.endswith("?"):
        return "Sure."
    else:
        return "Whatever."