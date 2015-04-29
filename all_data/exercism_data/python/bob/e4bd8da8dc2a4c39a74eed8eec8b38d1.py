import re
import unicodedata

class Bob():
    def __init__(self):
        pass

    def hey(self, prompt):
        if prompt.isspace() or not prompt:
            return 'Fine. Be that way!'
        if prompt[-1] == '.':
            prompt = prompt[:-1]
        if prompt[-1] == '!':
            prompt = prompt[:-1]
            if prompt.isupper():
                return 'Woah, chill out!'
            elif isinstance(prompt,unicode):
                if unicodedata.normalize('NFKD', prompt).encode('ascii','ignore').isupper():
                    return 'Woah, chill out!'
        if prompt[-1] == '?':
            prompt = prompt[:-1]
            prompt = re.sub("[^a-zA-Z]","", prompt)
            if not prompt.isupper():
                return 'Sure.'
        if prompt.isupper():
            return 'Woah, chill out!'
        else:
            return 'Whatever.'
