class Bob:
    def hey(self, str):
        str = str.strip()
        if str == '':
            return 'Fine. Be that way!'
        if str.isupper():
            return 'Woah, chill out!'
        if str[-1] == '?':
            return 'Sure.'
        return "Whatever."
