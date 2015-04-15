class Bob(object):

    def hey(self, message):
        nothing = not message or not message.strip()
        shout = message and message.isupper()
        question = message and message.endswith('?')

        if nothing:
            return('Fine. Be that way!')
        elif shout:
            return('Woah, chill out!')
        elif question:
            return 'Sure.'
        else:
            return('Whatever.')
