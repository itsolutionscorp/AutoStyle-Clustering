class Bob:
    def hey(self, statement):
        if statement.strip() == '':
            return 'Fine. Be that way!'
        if statement.isupper():
            return 'Woah, chill out!'
        if statement.strip()[-1] == '?':
            return 'Sure.'
        return 'Whatever.'

