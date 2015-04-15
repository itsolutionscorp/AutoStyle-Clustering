class bob:

    def hey(self, message):
        
        #This checks if there is no message (silence).
        if(len(message) == 0 or
           message.isspace()):
                return 'Fine. Be that way!'
        
        #This checks if all the letters are upper case (shouting).
        if(message.isupper()):
            return 'Whoa, chill out!'
        
        #This checks if the message is a question by checking last char.
        if(message[-1] == '?'):
            return 'Sure.'
            
        return "Whatever." 
