def hey(s):
  if s  == '' or s.replace(' ', '') == '' or s.replace('\n', '') == '' or s.replace('\t', '') == '' or s.replace(' ', '') == '':
    return 'Fine. Be that way!'
  elif s == s.upper() and s != s.lower():
    return 'Whoa, chill out!'
  elif s[-1] == '?':
    return 'Sure.'
  else:
    if not any(c.isalpha() for c in s) and not any(c.isnumeric() for c in s):
      return 'Fine. Be that way!'

    return 'Whatever.'
