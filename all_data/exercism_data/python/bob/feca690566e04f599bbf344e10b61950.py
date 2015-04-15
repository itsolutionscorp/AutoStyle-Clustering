class Bob(object):
    def hey(self, dialog):
        if self._addressed_without_saying_anything(dialog):
            return "Fine. Be that way!"
        elif self._yelled_at(dialog):
            return "Woah, chill out!"
        elif self._asked_a_question(dialog):
            return "Sure."
        else:
            return "Whatever."

    def _yelled_at(self, dialog):
        return dialog.isupper()

    def _addressed_without_saying_anything(self, dialog):
        return dialog == '' or dialog.isspace()

    def _asked_a_question(self, dialog):
        return dialog.endswith('?')
