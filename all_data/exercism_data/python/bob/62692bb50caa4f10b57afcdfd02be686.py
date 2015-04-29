class Bob(object):
    def __init__(self):
        self.reaction_to = {'silence': 'Fine. Be that way!',
                             'scream': 'Woah, chill out!',
                             'question': 'Sure.',
                             'anything_else': 'Whatever.',
                             }

    def hey(self, text):
        if text is None or not text.strip():
            return self.reaction_to['silence']
        elif text.upper() == text:
            return self.reaction_to['scream']
        elif text.endswith('?'):
            return self.reaction_to['question']
        return self.reaction_to['anything_else']
