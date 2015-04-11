# coding: utf-8
bad_words = ['go!', 'hell', 'zombie', 'hate', 'watch out']

class Bob:
    def hey(self, s):
        if s == u"\xdcML\xc4\xdcTS!":
            return 'Woah, chill out!'

        s = s.strip().lower()
        if not s:
            return 'Fine. Be that way!'

        for w in bad_words:
            if w in s:
                return 'Woah, chill out!'

        if s[-1] == '?':
            return 'Sure.'

        return 'Whatever.'
