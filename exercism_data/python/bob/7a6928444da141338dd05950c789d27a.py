class Bob:
    """ Bob class represent a BOT who has responses limited """
    
    def asking(self):
        """ Response when someone makes a question to Bob """
        return 'Sure.'

    def shouting(self):
        """ Response when someone shout out to Bob """
        return 'Whoa, chill out!'

    def silence(self):
        """ Response when someone say 'nothing' to Bob """
        return 'Fine. Be that way!'

    def whatever(sef):
        """ Default response of Bob """
        return 'Whatever.';

    def clean(self, what):
        """ Clen parameters """ 
        return what.strip()

    def speak(self, what):
        """ Main function to decide what Bob have to say """
        if isinstance(what, str):
            return self.whatever()

        what = self.clean(what)
        if not what or what == '':
            return self.silence()
        if what.isupper():
            return self.shouting()
        if what.endswith('?'):
            return self.asking()
        return self.whatever()


def hey(what):
    bob = Bob()
    return bob.speak(what)
