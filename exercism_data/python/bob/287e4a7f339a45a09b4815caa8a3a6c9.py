class Bob(object):
    def hey(self, message):
        if message.isupper():
            # Bob answers 'Woah, chill out!' if you yell at him.
            return 'Woah, chill out!'
        elif message.endswith('?'):
            # Bob answers 'Sure.' if you ask him a question.
            return 'Sure.'
        elif message.strip() == '':
            # Bob says 'Fine. Be that way!' if you address him without
            # actually saying anything.
            return 'Fine. Be that way!'
        else:
            # Bob answers 'Whatever.' to anything else.
            return 'Whatever.'
