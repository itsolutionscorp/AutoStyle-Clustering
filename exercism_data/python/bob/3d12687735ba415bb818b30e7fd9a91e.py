class Bob(object):

        def __init__(self):
            pass

        def hey(self, recieved_text):
            self.recieved_text = recieved_text
            if recieved_text:
                try:
                    if recieved_text.isupper():
                        return 'Woah, chill out!'
                    elif recieved_text[-1] == '?':
                        return 'Sure.'
                    elif recieved_text.isspace():
                        return 'Fine. Be that way!'
                    else:
                        return 'Whatever.'
            
                except:
                    pass
            else:
                return 'Fine. Be that way!'
