__author__ = 'ronvis'



def Bob():
    return bob()

def IsQuestion(request):
    if request.endswith('?'):
        return True
    else:
        return False

def IsEmpty(request):
    request = request.strip()
    if request == '':
        return True
    else:
        return False

def IsShout(request):
    if request.isupper():
        return True
    else:
        return False

class bob:

    defaultAnswer = 'Whatever.'

    def hey(self, request):
        response = self.defaultAnswer

        if IsEmpty(request):
            response = 'Fine. Be that way!'
        if IsQuestion(request):
            response = 'Sure.'
        if IsShout(request):
            response = 'Woah, chill out!'

        return response

