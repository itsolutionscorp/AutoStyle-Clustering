#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  sentence = ''
  if what.replace(" ","")[-1:] == '?' and what.isupper() is False:
    sentence = 'Sure.'
  else:
    if what.isupper():
      sentence = 'Whoa, chill out!'
    elif "".join(what.split()) == '':
      sentence = 'Fine. Be that way!'
    else:
      sentence = 'Whatever.'
  return sentence
