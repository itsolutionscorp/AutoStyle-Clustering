class Bob:
    def hey(self, i_am_talkin_to_you):
        if not i_am_talkin_to_you.strip():
            return 'Fine. Be that way!'
        if i_am_talkin_to_you.isupper():
            return 'Woah, chill out!'
        if i_am_talkin_to_you.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
