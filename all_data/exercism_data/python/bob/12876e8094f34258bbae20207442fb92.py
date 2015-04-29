import bob

class Bob():
    def hey(self, greeting):
        self.greeting = bob.Greeting()
        return self.greeting.response(greeting)

class Greeting():
    def response(self, greeting):
        if (greeting == None) : greeting = ''
        if (self.yelling(greeting)) : return "Woah, chill out!"
        elif (self.asking(greeting)) : return "Sure."
        elif (self.silent(greeting)) : return "Fine. Be that way!"
        else : return "Whatever."

    def yelling(self, greeting):
        if (greeting.isupper()) : return True
        else : return False

    def asking(self, greeting):
        if (greeting.endswith("?")) : return True
        else : return False

    def silent(self, greeting):
        if (len(greeting.strip()) == 0) : return True
        else : return False
