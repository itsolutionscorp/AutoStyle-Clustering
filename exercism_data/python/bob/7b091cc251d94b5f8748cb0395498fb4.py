import re

class Bob:
    def hey(self, message):
        if re.search(r"^[^a-z]*[A-Z]+[^a-z]*$", message):
            all_caps = True
            for char in message:
                if char.islower(): # tricky unicode characters...
                    all_caps = False
                    break
            if all_caps:
                return 'Woah, chill out!'
        if re.search(r"\?$", message):
            return 'Sure.'
        if re.search(r"^\s*$", message):
            return 'Fine. Be that way!'
        return 'Whatever.'
