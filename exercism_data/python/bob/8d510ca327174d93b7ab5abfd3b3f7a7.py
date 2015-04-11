def is_blank(message):
    return message is ''

def all_caps(message):
    return message.upper() == message

def is_question(message):
    return message.endswith('?')

def is_statement(message):
    return message.endswith('.')

class Bob(object):

    def hey(self, message):
        ''' Returns a response according to format of message'''
        self.message = message
        if is_blank(message):
            return 'Fine. Be that way.'
        elif all_caps(message):
            return 'Woah, chill out!'
        elif is_question(message):
            return 'Sure.'
        elif is_statement(message):
            return 'Whatever.'
        else:
            return "Sometimes no one tells me what to say."
