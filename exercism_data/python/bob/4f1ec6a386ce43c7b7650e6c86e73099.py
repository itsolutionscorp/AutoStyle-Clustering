def hey(what):
  if not what or what.isspace() :
    return 'Fine. Be that way!'

  if what[:-1].upper() == what[:-1] :
      if any(c.isalpha() for c in what):
        return "Whoa, chill out!"

  if what[-1] == '?' :
    return 'Sure.'

  return 'Whatever.'
