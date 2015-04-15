class Bob(object):

    def __init__(self):
        pass

    def hey(self, message):
        """Returns Bob's response to message.

        @param message: message to Bob
        @type message: string

        @return: string with Bob's response
        """

        if message is None or len(message)==0 or message.isspace():
            return 'Fine. Be that way!'

        # Check if there are no lowercase letters.
        if not any(c for c in message if c.islower()):
            return 'Woah, chill out!'

        if message[-1]=='?':
            return 'Sure.'

        return 'Whatever.'
