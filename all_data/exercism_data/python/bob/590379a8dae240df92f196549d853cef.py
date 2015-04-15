class Bob( object ):
    whatever = "Whatever."
    shouting = "Woah, chill out!"
    grumpy = "Fine. Be that way!"
    answerquestion = 'Sure.'

    def hey( self, msg ):
        """ Returns an appropriate answer to msg """ 

        reply = None # What to answer

        # Test empty msg
        if not msg or msg.isspace() :
            reply =  self.grumpy
        
        # Test uppercase
        elif msg.isupper():
            reply = self.shouting

        # Test question
        elif msg.endswith('?'):
            reply = self.answerquestion

        # Return whatever otherwise
        else:
            reply = self.whatever

        return reply
