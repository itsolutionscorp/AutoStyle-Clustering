'''exer phone number formatter'''

import string

class Phone(object):
    '''handle parsing/checking and formatting phone numbers'''

    def __init__(self, phone_string):
        self.number = ''.join([x for x in phone_string
                               if x in string.digits])
        if len(self.number) == 11:
            if self.number.startswith('1'):
                self.number = self.number[1:]
        if len(self.number) != 10:
            self.number = '0' * 10


    def pretty(self):
        '''return a prettified phone number (xxx) yyy-zzzz'''
        return '(%s) %s-%s' % (self.number[:3], self.number[3:6],
                               self.number[-4:])

    def area_code(self):
        '''return just the area code'''
        return self.number[:3]
