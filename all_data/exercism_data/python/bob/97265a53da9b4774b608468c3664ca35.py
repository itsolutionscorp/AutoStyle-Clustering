__author__ = 'ronvis'

def Bob():
    return Bob()

def IsQuestion(request):
    return request.endswith('?')

def IsEmpty(request):
    if request.strip():
        return True
    else:
        return False

def IsShout(request):
    return request.isupper()

class Bob:

    DEAFAULT_ANSWER = 'Whatever.'
    QUESTION_RESPONSE = 'Sure.'
    EMPTY_RESPONSE = 'Fine. Be that way!'
    SHOUT_RESPONSE = 'Woah, chill out!'

    def hey(self, request):
        response = self.DEAFAULT_ANSWER

        if IsEmpty(request):
            response = self.EMPTY_RESPONSE
        if IsQuestion(request):
            response = self.QUESTION_RESPONSE
        if IsShout(request):
            response = self.SHOUT_RESPONSE

        return response

