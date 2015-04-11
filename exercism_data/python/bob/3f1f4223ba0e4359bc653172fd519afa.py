class Bob(object):
    '''Bob is a guy with a very limited vocabulary'''

    @staticmethod
    def hey(sentence):
        '''You speak to Bob with this method'''
        if not sentence or sentence.isspace():
            # He says 'Fine. Be that way!' if you address him without
            # actually saying anything.
            return 'Fine. Be that way!'
        elif sentence.isupper():
            # He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
            return 'Woah, chill out!'
        elif sentence.endswith('?'):
            # Bob answers 'Sure.' if you ask him a question.
            return 'Sure.'
        else:
            # He answers 'Whatever.' to anything else.
            return 'Whatever.'
