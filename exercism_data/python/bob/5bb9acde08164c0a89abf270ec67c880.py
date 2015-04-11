class Bob():

    def _sanitize(self, ipt):
        return '' if ipt is None else str(ipt).strip()

    def _is_empty(self, ipt):
        return not len(ipt)

    def _is_shouting(self, ipt):
        for c in ipt:
            if c >= 'a' and c <= 'z':
                return False
        return True

    def _is_questioning(self, ipt):
        return ipt.endswith("?")

    def hey(self, ipt):
        ipt = self._sanitize(ipt)

        if self._is_empty(ipt):
            out = "Fine. Be that way!"
        elif self._is_shouting(ipt):
            out = "Woah, chill out!"
        elif self._is_questioning(ipt):
            out = "Sure."
        else:
            out = "Whatever."

        return out
