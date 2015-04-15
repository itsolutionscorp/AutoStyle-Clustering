class Bob(object):
    responses = {
            'silent' : 'Fine. Be that way!',
            'yell' : 'Woah, chill out!',
            'question' : 'Sure.',
            'else' : 'Whatever.' }

    def hey(self, message):
        input_category = Message(message).category()
        return self.responses[input_category]

class Message(object):

    def __init__(self, text):
        self.text = text


    def category(self):
        if self.is_silent():
            return 'silent'
        elif self.is_yell():
            return 'yell'
        elif self.is_question():
            return 'question'
        else:
            return 'else'
        
    def is_question(self):
        return (len(self.text) > 0) and self.text[-1] == '?'

    def is_yell(self):
        return (self.text == self.text.upper() != self.text.lower())
    
    def is_silent(self):
        return not self.text.strip()
