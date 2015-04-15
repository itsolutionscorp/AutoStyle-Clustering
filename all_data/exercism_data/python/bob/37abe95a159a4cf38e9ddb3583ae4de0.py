import bob

class Bob():
    def hey(self, greeting):
        self.greeting = bob.Greeting(greeting)
        return self.response()

    def response(self):
        if self.greeting.yelling():
            return "Woah, chill out!"
        if self.greeting.asking():
            return "Sure."
        if self.greeting.silent():
            return "Fine. Be that way!"
        return "Whatever."
        
class Greeting():
    def __init__(self, greeting):
        if greeting == None:
            greeting = ''
        self.greeting = greeting
        
    def yelling(self):
        return self.greeting.isupper()

    def asking(self):
        return self.greeting.endswith("?")

    def silent(self):
        return len(self.greeting.strip()) == 0
        
