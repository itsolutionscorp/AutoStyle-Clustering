def hey(statement):
    #remove unneeded whitespace
    statement = statement.strip()

    #responses
    whatevs = "Whatever."
    chills = "Woah, chill out!"
    sures = "Sure."
    fines = "Fine. Be that way!"

    #answer selection
    if not statement:
        return fines
    elif statement.isupper():
        return chills
    elif statement[-1] == "?":
        return sures
    else:
        return whatevs
