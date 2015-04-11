def hey(self, statement):
    if statement.strip() == '':
        return "Fine. Be that way!"
    elif statement.isupper():
        return "Whoa, chill out!"
    elif statement.endswith("?"):
        return "Sure."
    else:
        return "Whatever."
