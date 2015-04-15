class Bob(object):

    def hey(self, query):
        if not query or not query.strip():
            return 'Fine. Be that way!'
        if query.upper() == query:
            return 'Woah, chill out!'
        if query[-1] == '?':
            return 'Sure.'
        return 'Whatever.'
