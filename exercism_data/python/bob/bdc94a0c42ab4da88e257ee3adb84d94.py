class Bob(object):
    def hey(self, s):
        s = s.strip()
        if not s:
            return "Fine. Be that way!"
        if shouting(s):
            return "Woah, chill out!"
        if s.endswith("?"):
            return "Sure."
        return "Whatever."

# all caps considered shouting
# rule: at least one UC letter and no LC letters
def shouting(s):
    uc = False
    for r in s:
        if s.islower():
            return False
        if s.isupper():
            uc = True
    return uc
