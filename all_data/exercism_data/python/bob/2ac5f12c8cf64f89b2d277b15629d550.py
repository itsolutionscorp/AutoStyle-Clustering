class Person():
    def __init__(self):
        self.data = []

    @staticmethod
    def hey(text):
        if text.isspace() or not text:
            return "Fine. Be that way!"
        if text.endswith('?'):
            if not text.islower() and not text.isupper():
                return "Sure."
            elif text.isupper():
                return "Whoa, chill out!"
        if text.endswith('!') or text.isupper():
            if not text.islower() and not text.isupper():
                return "Whatever."
            if text.isupper():
                return "Whoa, chill out!"
        if text.isdigit():
            return "Whatever(isdigit)."
        if not text.islower() or not text.isupper():
            return "Whatever."


bob = Person()
