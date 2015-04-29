class Bob():
    def hey(self, s):
        s = s.strip()
        excl = 'Woah, chill out!'
        ques = 'Sure.'
        whatever = 'Whatever.'
        silent = 'Fine. Be that way!'
        numbers = [str(n) for n in range(10)]
        num_numbers = len([c for c in s if c in numbers])
        is_mostly_numbers = (num_numbers / (len(s) + 0.5)) > 0.3
        if not s:
            return silent
        elif s.upper() == s and not is_mostly_numbers:
            return excl
        elif s.endswith('?'):
            return ques
        elif is_mostly_numbers:
            return whatever
        elif s.endswith('!') and s.upper() == s:
            return excl
        else:
            return whatever
