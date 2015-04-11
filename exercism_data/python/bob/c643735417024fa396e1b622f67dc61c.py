#
# Skeleton file for the Python "Bob" exercise.
#
class Bob:

  def hey(self, message):
      response = "Whatever."
      if message.isupper():
        response = "Woah, chill out!"
      elif message.endswith("?"):
        response = "Sure."
      elif message.strip() == "":
        response = "Fine. Be that way!"


      return response
