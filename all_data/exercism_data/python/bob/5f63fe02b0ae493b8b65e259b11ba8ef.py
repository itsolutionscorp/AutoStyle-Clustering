class Bob:
        def hey(self, s):
                if s.endswith("?") and s.isupper():
                        return 'Woah, chill out!'
                if s.endswith("?"):
                        return 'Sure.'
                if s.isupper():
                        return 'Woah, chill out!'
                if s.islower():
                        return 'Whatever.'
                if len(s.strip())==0:
                        return 'Fine. Be that way!'
                else: return 'Whatever.'
