import re

class Bob:
        def hey(self, x):
                # http://stackoverflow.com/questions/2039140/python-re-how-do-i-match-an-alpha-character
                anyalpha = re.search("[^\W\d_]+", x, re.UNICODE) is not None
                allwhite = re.search("^\s*$", x, re.UNICODE) is not None
                if anyalpha and x.upper() == x:
                        return 'Woah, chill out!'
                elif x.endswith('?'):
                        return 'Sure.'
                elif allwhite:
                        return 'Fine. Be that way!'
                else:
                        return 'Whatever.'
