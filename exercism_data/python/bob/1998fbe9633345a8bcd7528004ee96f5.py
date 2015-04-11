#z
# Skeleton file for the Python "Bob" exercise.
#

def hey(self):
    if self.strip() == "":
        return "Fine. Be that way!"
    elif self.isupper():
      return "Whoa, chill out!"
    elif self.strip().endswith("?"):
      return "Sure."
    else:
        return "Whatever."
