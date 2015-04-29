# import re

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
#         return msg.upper() == msg and re.search(r'[^\W\d_]+', msg, re.UNICODE) is not None
        return msg.isupper()

    def is_question(self, msg):
        ''' 
        A string that ends in a question mark is a question. Duh!
        In order to keep this function self sufficient, we also make
        sure it is not a yell. This makes the order of checks irrelevant.
        '''
#         return re.search(r'\?$', msg) is not None
        return msg.endswith('?') and not msg.isupper()

    def is_silence(self, msg):
        ''' A string with only white noise.'''
#         return re.match(r'^\s*$', msg) is not None
        return msg.strip() == ''
    
    def hey(self, msg):
        '''
        Public API to speak with a Teaanager.
        '''
        if self.is_silence(msg):
            return self.responses['silence']
        elif self.is_yelling(msg):
            return self.responses['yell']
        elif self.is_question(msg):
            return self.responses['question']
        else:
            return self.responses['default']
