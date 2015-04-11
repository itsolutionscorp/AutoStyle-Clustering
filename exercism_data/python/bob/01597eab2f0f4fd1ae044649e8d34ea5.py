class Bob(object):
    def __init__(self):
        self.react_to = {'silence': 'Fine. Be that way!',
                         'scream': 'Woah, chill out!',
                         'question': 'Sure.',
                         'anything_else': 'Whatever.',
                         }

    def hey(self, text):
        if text is None or not text.strip():
            return self.react_to['silence']
        elif text.upper() == text:
            return self.react_to['scream']
        elif text.endswith('?'):
            return self.react_to['question']
        return self.react_to['anything_else']
