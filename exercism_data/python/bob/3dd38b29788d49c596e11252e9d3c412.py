#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  # Determine if Bob is being shouted at. ::isupper() returns true only if there is a cased letter;
  # numerical arguments return false
  if what.isupper():
    return "Whoa, chill out!"

  # Parses the argument and checks if it is an empty string
  if what.strip() == "" or what.lstrip() == "":
    return "Fine. Be that way!"
  
  # Parses the string, then determines if the last character is a question mark
  if what.strip()[len(what.strip())-1] == "?":
    return "Sure."

  # Return "Whatever" as default.
  return "Whatever."
  
