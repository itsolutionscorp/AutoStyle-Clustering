from operator import __or__, __and__

class Bob(object):
    def hey(self, string):
        if string.strip() == '':
            return "Fine. Be that way!"
        elif self._is_yell(string):
            return "Woah, chill out!"
        elif list(string)[-1] == '?':
            return "Sure."
        else:
            return "Whatever."

    def _is_yell(self, string):
        filtered = filter(lambda x: x.isalpha(), string)
        uppers = map(lambda x: x.isupper(), filtered)

        if len(uppers) == 0:
            return False
        else:
            return reduce(__and__, uppers, True)
