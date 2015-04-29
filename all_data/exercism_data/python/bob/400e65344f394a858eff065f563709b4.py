# message type constants
QUESTION = 'question'
ALLCAPS = 'allcaps'
EMPTY = 'empty'
OTHER = 'other'


class Bob:
    """ Lackadaisical teenager that responds in some special way to your messages """
    def hey(self, message):
        """ Send Bob a message

        @param message: string that represents an inbound message to Bob
        @return: responce string from Bob
        """
        responce_map = {QUESTION: 'Sure.',
                        ALLCAPS: 'Woah, chill out!',
                        EMPTY: 'Fine. Be that way!',
                        OTHER: 'Whatever.',
        }
        if message:
            message = message.strip()
        message_type = self.define_message_type(message)
        return responce_map[message_type]

    def define_message_type(self, message):
        """ Determine the type of the inbound message

        @param message: string that represents an inbound message to Bob
        @return: string that is represents inbound message type
        """
        if not message:
            return EMPTY
        elif message.isupper():
            return ALLCAPS
        elif message.endswith('?'):
            return QUESTION
        else:
            return OTHER
