class Bob(object):
    
    def hey(self, question):
        if question.replace(' ', '') == '':
            return 'Fine. Be that way!'
        if question.upper() == question and question.upper() != question.lower():
            return 'Woah, chill out!'
        if question.endswith('?'):
            return 'Sure.'
        
        return 'Whatever.'
