# vim: set ts=4 sts=4 sw=4 et:

class Bob:
    def hey(self, message):
        if (message is None or message.strip() == ''):
            return 'Fine. Be that way!'

        if (message.isupper()):
            return 'Woah, chill out!'

        if (message.endswith("?")):
            return 'Sure.'

        return 'Whatever.'
