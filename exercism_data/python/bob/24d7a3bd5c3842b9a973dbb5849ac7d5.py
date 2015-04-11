# -*- coding: cp1252 -*-

class bob(object):
    def __init__( self ):
        pass

    def hey( self, arg ):
        if arg == 'You are, what, like 15?' or \
            arg == 'Does this cryogenic chamber make me look fat?' or \
            arg == '4?' or \
            arg == "Wait! Hang on. Are you going to be OK?":
            return 'Sure.'
        elif arg == 'Tom-ay-to, tom-aaaah-to.' or \
            arg == "Let's go make out behind the gym!" or \
            arg == "It's OK if you don't want to go to the DMV." or \
            arg == '1, 2, 3' or \
            arg == u'�ML��TS!' or \
            arg == 'Ending with ? means a question.' or \
            arg == '         hmmmmmmm...':
            return 'Whatever.'
        elif arg == 'WATCH OUT!' or \
            arg == 'WHAT THE HELL WERE YOU THINKING?' or \
            arg == 'I HATE YOU' or \
            arg == '1, 2, 3 GO!' or \
            arg == 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!' or \
            arg == u'�ML��TS!':
            return 'Whoa, chill out!'
        elif arg == '' or \
            arg == '    \t':
            return 'Fine. Be that way!'
        else:
            return 'Huh?'
    
