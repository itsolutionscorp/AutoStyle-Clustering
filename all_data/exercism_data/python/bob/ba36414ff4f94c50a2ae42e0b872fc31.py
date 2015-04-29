class Bob(object):
    def hey(self, phrase):
        if ''.join([i for i in phrase if i.isalpha()]).isupper():
            return 'Woah, chill out!'
        if phrase.endswith('?'): return 'Sure.' 
        if phrase.strip() == '': return 'Fine. Be that way!'
        return 'Whatever.'
