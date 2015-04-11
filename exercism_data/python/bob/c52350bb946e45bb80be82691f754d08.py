class Bob:

    def __init__(self):
        self.name = "Bob"

    def hey(self, message):
        if not message or not message.split():
            response = "Fine. Be that way!"
        elif message.isupper():
            response = "Woah, chill out!"
        elif message[-1] == "?":
            response = "Sure."
        else:
            response = "Whatever."
        
        return response
