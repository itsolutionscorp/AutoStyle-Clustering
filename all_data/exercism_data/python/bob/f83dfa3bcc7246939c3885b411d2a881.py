from operator import __or__, __and__

class Bob(object):
    def hey(self, string):
        if string.strip() == '':
            return "Fine. Be that way!"
        elif self._is_yell(string):
            return "Woah, chill out!"
        elif len(string) > 0 and list(string)[-1] == '?':
            return "Sure."
        else:
            return "Whatever."

    def _is_yell(self, string):
        return (self._all_not_lower(string) and self._one_is_upper(string))

    def _all_not_lower(self, string):
        return reduce(__and__, map(lambda x: not x.islower(), list(string)), True)

    def _one_is_upper(self, string):
        return reduce(__or__, map(lambda x: x.isupper(),list(string)), False)
