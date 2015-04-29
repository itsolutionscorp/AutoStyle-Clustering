def is_question(greeting):
    if len(greeting) > 1 and greeting[-1] == "?":
        return True
    return False

def is_yelling(greeting):
    if greeting.upper() == greeting:
        if any(c.isalpha() for c in greeting):
            return True
    return False

def is_addressing(greeting):
    if not greeting.strip():
        return True
    return False

class Bob:
    def __init__(self):
        self.response = {
            'question': 'Sure.',
            'yell': 'Woah, chill out!',
            'address': 'Fine. Be that way!',
            'default': 'Whatever.'
            }
        
    def hey(self, greeting):
        if is_yelling(greeting):
            return self.response['yell']
        elif is_question(greeting):
            return self.response['question']
        elif is_addressing(greeting):
            return self.response['address']
        else:
            return self.response['default']
            
        
