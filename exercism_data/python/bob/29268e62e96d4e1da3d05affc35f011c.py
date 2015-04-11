import bob

class Bob(object):
    def hey(self, greeting):
        return self.response(greeting)

    def response(self, greeting):
        self.greeting = bob.Greeting(greeting)
        if self.greeting.yelling():
            return "Woah, chill out!"
        if self.greeting.asking():
            return "Sure."
        if self.greeting.silent():
            return "Fine. Be that way!"
        return "Whatever."
        
class Greeting(object):
    def __init__(self, greeting):
        self.greeting = greeting or ''
        
    def yelling(self):
        return self.greeting.isupper()

    def asking(self):
        return self.greeting.endswith("?")

    def silent(self):
        return len(self.greeting.strip()) == 0
        
