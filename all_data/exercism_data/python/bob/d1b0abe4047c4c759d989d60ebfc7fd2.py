class Bob(object):
    """ Lackadaisical teenager that responds in some special way to your messages """

    def hey(self, message):
        """ Send Bob a message and get a response

        @param message: string that represents an inbound message to Bob
        @return: response string from Bob
        """
        message = message.strip() if message else message
        if not message:                     # address without saying anything
            response = 'Fine. Be that way!'
        elif message.isupper():             # shout
            response = 'Woah, chill out!'
        elif message.endswith('?'):         # ask question
            response = 'Sure.'
        else:
            response = 'Whatever.'
        return response
