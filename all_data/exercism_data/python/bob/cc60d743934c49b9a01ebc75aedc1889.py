'''
Bob Solution
'''


class Bob(object):
    '''
    A lacksadiacal teen with agst issues.
    '''
    def hey(self, request):
        '''
        Builds a response to request.
        '''
        # Request isn't empty or blank space
        if request and not request.isspace():
            # Request isn't yelling and is a question
            if request[-1] is '?' and not request.isupper():
                response = "Sure."
            # Request is yelling
            elif request.isupper():
                response = "Woah, chill out!"
            # Other
            else:
                response = 'Whatever.'
        else:
            response = 'Fine. Be that way!'
        return response
