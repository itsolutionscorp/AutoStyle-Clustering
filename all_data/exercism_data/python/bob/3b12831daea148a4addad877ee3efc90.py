class Bob:
    def hey(self, s0):
        if s0 is None:
            return "Fine. Be that way!"
        if isinstance(s0, basestring):
            s = unicode(s0, "latin-1")
        if self.is_nothing(s):
            return "Fine. Be that way!"
        elif self.is_yelled(s):
            return "Woah, chill out!"
        elif self.is_question(s):
            return "Sure."
        else:
            return "Whatever."

    def is_nothing(self, s):
        return s.strip() == ""

    def is_yelled(self, s):
        return s.isupper()

    def is_question(self, s):
        return len(s) > 0 and s[-1] == "?"

execfile('bob_test.py')
