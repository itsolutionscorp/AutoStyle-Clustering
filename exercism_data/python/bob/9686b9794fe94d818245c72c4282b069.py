class Bob(object):
    """ 
    Bob is a lackadaisical teenager. In conversation, his responses are very 
    limited. Talk to him be calling `hey`.
    """ 

    def hey(self, statement):
        """ Address Bob with a `statement` and get an elliptic response. """

        if statement.isupper():
            return "Woah, chill out!"
        if statement.isspace() or statement is "":
            return "Fine. Be that way!"
        if statement[-1] is "?":
            return "Sure."
        return "Whatever."
