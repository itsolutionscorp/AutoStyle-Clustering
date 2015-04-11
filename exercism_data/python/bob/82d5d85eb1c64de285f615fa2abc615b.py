class Bob(object):
    def hey(self, phrase):
        if not phrase or phrase.replace(" ", "") == "":
            return "Fine. Be that way!"
        elif phrase.upper() == phrase:
            return 'Woah, chill out!'
        elif phrase[-1] == '.':
            return 'Whatever.'
        elif phrase[-1] == '?':
            return 'Sure.'
        elif phrase[-1] == '!':
            return 'Whatever.'
