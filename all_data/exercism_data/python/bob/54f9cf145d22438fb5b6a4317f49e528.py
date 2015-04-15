class Bob(object):
    def hey(self, prompt):
        if prompt.isupper():
            return 'Woah, chill out!'
        if prompt.endswith('?'):
            return 'Sure.'
        if prompt.strip() == "":
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
