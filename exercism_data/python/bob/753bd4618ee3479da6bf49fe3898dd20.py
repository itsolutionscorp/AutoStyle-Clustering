import re
class Bob:
    def hey(self, q):
        if q.strip() == '':
            return 'Fine. Be that way!'
        if q == q.upper() and re.search('[a-zA-Z]', q):
            return 'Woah, chill out!'
        if q.strip()[-1] == '?':
            return 'Sure.'
        return 'Whatever.'
