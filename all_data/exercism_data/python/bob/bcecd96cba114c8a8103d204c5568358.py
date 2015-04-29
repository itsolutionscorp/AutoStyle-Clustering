class Bob(object):

    def hey(self, s):

        if not self.is_sentence(s):
            if s[-1] is '?':
                r = 'Sure.'
            else:
                r = 'Whatever.'

        elif s.isspace() or len(s) is 0:
            r = 'Fine. Be that way!'

        elif s.upper() == s:
            r = 'Woah, chill out!'

        elif s[-1] is '?':
            r = 'Sure.'

        else:
            r = 'Whatever.'

        return r

    def is_sentence(self, s):
        n = s.replace(" ", "")
        n = n.replace(',', '')
        n = n.replace('?', '')

        if n.isdigit() is True:
            return False
        else:
            return True
