#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  sure = 'Sure.'
  whatever = 'Whatever.'
  whoa = 'Whoa, chill out!'
  fine = 'Fine. Be that way!'

  if what[-1] == '?':
    return sure
  if what == '':
    return fine
  if what[0:-1].upper() == what[0:-1]:
    return whoa
  # if what[-1:0] == '!':
  #   return whoa
  else:
    return whatever
