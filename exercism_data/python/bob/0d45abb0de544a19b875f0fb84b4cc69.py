'''bob file for exercism'''


class Bob():
    '''the Bob class'''
    def __init__(self):
        '''initialising Bob class'''
        pass

    def hey(self, shout):
        '''Shouting at bob'''
        # Empty calls
        if shout is None or shout.strip() == "":
            return 'Fine. Be that way!'
        # Shouting at Bob
        elif (shout.upper() == shout):
            return 'Woah, chill out!'
        # Asking
        elif shout[-1] == "?":
            return 'Sure.'
        # Everything else
        else:
            return 'Whatever.'
