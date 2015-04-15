import re

class Bob(object):
    '''
    Pass the bob test!
    '''
    responses = {'yell': 'Woah, chill out!',
                 'question': 'Sure.',
                 'silence': 'Fine. Be that way!', 
                 'default': 'Whatever.'}
    
    def is_yelling(self, msg):
        ''' 
        A string in all caps is tantamount to shouting.
        But it should have some substance atleast.
        '''
        return msg.upper() == msg and re.search(r'[^\W\d_]+', msg, re.UNICODE) is not None

    def is_question(self, msg):
        ''' 
        A string that ends in a question mark is a question. Duh!
        '''
        return re.search(r'\?$', msg) is not None

    def is_silence(self, msg):
        ''' A string with only white noise.'''
        return re.match(r'^\s*$', msg) is not None

    def hey(self, msg):
        '''
        Public API to speak with a Teaanager.
        '''
        if self.is_silence(msg):
            return self.responses['silence']
        elif self.is_yelling(msg):
            # We first check for yelling since a yelled out question is a yell
            return self.responses['yell']
        elif self.is_question(msg):
            return self.responses['question']
        else:
            return self.responses['default']
