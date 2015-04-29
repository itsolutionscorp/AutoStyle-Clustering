def hey(thing):
  thing_l = ''.join(e for e in thing if e.isalnum())
  is_upper = True
  contains_alpha = False
  for ul in thing_l:
    if is_upper:
      if ul.isupper() or ul.isdigit():
        pass
      else:
        is_upper = False
  for i in thing_l:
    if i.isalpha():
      contains_alpha = True

  if len(thing_l) == 0:
    return 'Fine. Be that way!'
  elif (is_upper or (is_upper and thing.endswith('?'))) and contains_alpha:
    return 'Woah, chill out!'
  elif thing.endswith('?'):
    return 'Sure.'
  else:
    return 'Whatever.'
