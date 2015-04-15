class Bob:

    def __init__(self):
        pass

    def hey(self, statement):
        if statement is None:
            return 'Fine. Be that way.'
        elif statement is '' or statement.isspace():
            return 'Fine. Be that way.'
        elif statement.isupper():
            return 'Woah, chill out!'
        elif statement[-1] == "?":
            return "Sure."
        return "Whatever."

b= Bob()
print b.hey(raw_input("?"))
