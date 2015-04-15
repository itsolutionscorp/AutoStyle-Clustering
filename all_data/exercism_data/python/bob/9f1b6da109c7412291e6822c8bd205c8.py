import bob

class Bob():
    def hey(self, greeting):
        self.greeting = bob.Greeting(greeting)
        return self.greeting.response()
        
class Greeting():
    def __init__(self, greeting):
        if greeting == None:
            greeting = ''
        self.greeting = greeting
        
    def response(self):
        if self.yelling():
            return "Woah, chill out!"
        if self.asking():
            return "Sure."
        if self.silent():
            return "Fine. Be that way!"
        return "Whatever."
        
    def yelling(self):
        return self.greeting.isupper()

    def asking(self):
        return self.greeting.endswith("?")

    def silent(self):
        return len(self.greeting.strip()) == 0
