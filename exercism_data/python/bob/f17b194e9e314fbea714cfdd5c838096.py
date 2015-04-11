class Bob(object):
    """ Lackadaisical teenager that responds in some special way to your messages """

    def hey(self, message):
        """ Send Bob a message and get a response

        @param message: string that represents an inbound message to Bob
        @return: response string from Bob
        """
        message = message.strip() if message else message
        # address without saying anything
        if not message:
            response = 'Fine. Be that way!'
        # shout
        elif message.isupper():
            response = 'Woah, chill out!'
        # ask question
        elif message.endswith('?'):
            response = 'Sure.'
        else:
            response = 'Whatever.'
        return response
