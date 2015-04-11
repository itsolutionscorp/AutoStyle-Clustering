class Bob(object):
  
    def hey(self, text):
        message = Message(text)
        dispatcher = MethodDispatcher(self._responses(message), self._neutral_response)
        return dispatcher.dispatch()
        
    def _neutral_response(self):
        return 'Whatever.'

    def _annoyed_reponse(self):
        return 'Fine. Be that way!'

    def _approval_response(self):
        return 'Sure.'

    def _anxious_response(self):
        return 'Woah, chill out!'

    def _responses(self, message):
        return { 
          message.is_silent: self._annoyed_reponse,
          message.is_yell: self._anxious_response,
          message.is_question: self._approval_response 
        }


class Message(object):

    def __init__(self, text):
        self.text = str(text)

    def is_silent(self):
        return self.text == 'None' or self.text.strip() == ""

    def is_yell(self):
        return self.text.isupper()

    def is_question(self):
        return self.text.endswith("?")
    

class MethodDispatcher(object):
    
    def __init__(self, methods_store, default_action):
        self.methods_store = methods_store
        self.default_action = default_action

    def dispatch(self):
        for condition, action in self.methods_store.iteritems():
            if condition(): return action()
        else:
            return self.default_action()
        
