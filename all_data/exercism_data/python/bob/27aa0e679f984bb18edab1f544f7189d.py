def hey(statement):
    statement = statement.strip()
    if not statement:
        return "Fine. Be that way!"
    if statement.isupper():
        return "Whoa, chill out!"
    if statement.endswith("?"):
        return "Sure."
    return "Whatever."
