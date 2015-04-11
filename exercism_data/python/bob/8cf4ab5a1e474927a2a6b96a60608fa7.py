from string import ascii_letters, digits

def hey(statement):
    for char in statement:
        if char in ascii_letters + digits:
            break
    else:
        return "Fine. Be that way!"
    if statement.isupper():
        return "Whoa, chill out!"
    if statement[-1] == "?":
        return "Sure."
    return "Whatever."
