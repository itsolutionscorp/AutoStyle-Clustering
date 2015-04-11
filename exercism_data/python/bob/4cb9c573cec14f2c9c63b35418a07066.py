def _silent(message):
    return message.strip() == ''

def _yelling(message):
    return message.upper() == message

def _querying(message):
    return message.endswith('?')

class Bob():
    def hey(self, message):
        if(message is None): message = ''
        if(_silent(message)):
            return 'Fine. Be that way!'
        if(_yelling(message)):
            return 'Woah, chill out!'
        if(_querying(message)):
            return 'Sure.'
        return 'Whatever.'
