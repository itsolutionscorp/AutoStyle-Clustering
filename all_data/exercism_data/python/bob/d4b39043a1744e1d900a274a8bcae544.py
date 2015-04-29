import string
class Bob(object):

        """Constants"""
        FINE= 'Fine. Be that way!'
        CHILL= 'Woah, chill out!'
        SURE= 'Sure.'
        WHATEVER= 'Whatever.'

        def __init__(self):
                pass

        def hey(self,chat):
                if self.__isEmpty(chat):
                        return Bob.FINE
                elif self.__isHostile(chat):
                        return Bob.CHILL
                elif self.__isQuestion(chat):
                        return Bob.SURE 
                return Bob.WHATEVER
                
        def __isHostile(self,chat):
                return chat.isupper()

        def __isQuestion(self,chat):
                return chat.endswith('?') 

        def __isEmpty(self,chat):
                return len(string.strip(chat))==0
