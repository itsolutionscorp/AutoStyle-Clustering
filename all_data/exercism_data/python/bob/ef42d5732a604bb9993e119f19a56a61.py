class Bob(object):
    """docstring for Bob"""
    def __init__(self):
        super(Bob, self).__init__()

    def give_default_response(self):
        return "Whatever."

    def respond_to_question(self):
        return "Sure."

    def respond_to_yelling(self):
        return "Woah, chill out!"
        
    def respond_to_silence(self):
        return 'Fine. Be that way!'

    def hey(self, message_content):
        message = Message(message_content)
        if message.is_yelling():
            return self.respond_to_yelling()
        if message.is_question():
            return self.respond_to_question()
        if message.is_only_silence():
            return self.respond_to_silence()
        return self.give_default_response()
        
class Message(object):
    """docstring for Message"""
    def __init__(self, content=None):
        super(Message, self).__init__()
        if content is None:
            content =u""
        self.content = content.strip().encode('utf-8').decode('utf-8', 'replace')
        
    def is_question(self):
        return self.content.endswith("?")
    
    def is_yelling(self):
        return self.content.isupper()

    def is_only_silence(self):
        return not bool(self.content)
