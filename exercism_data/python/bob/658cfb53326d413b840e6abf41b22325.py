class Bob(object):

    def hey(self, s):

        reaction = {
            'yell': 'Woah, chill out!',
            'question': 'Sure.',
            'blink': 'Fine. Be that way!',
            'nothing': 'Whatever.'
        }

        if self.is_blink(s):
            r = 'blink'
        elif not self.is_alphabetical_sentence(s):
            if self.is_question(s):
                r = 'question'
            else:
                r = 'nothing'
        elif self.is_yell(s):
            r = 'yell'
        elif self.is_question(s):
            r = 'question'
        else:
            r = 'nothing'

        return reaction[r]

    def is_alphabetical_sentence(self, s):
        n = s.replace(" ", "")
        n = n.replace(',', '')
        n = n.replace('?', '')

        if n.isdigit() is True:
            return False
        else:
            return True

    def is_question(self, s):
        if s[-1] is '?':
            return True
        else:
            return False

    def is_yell(self, s):
        n = s.replace('?', '')
        if n.upper() == n:
            return True
        else:
            return False

    def is_blink(self, s):
        if len(s) is 0 or s.isspace():
            return True
        else:
            return False
