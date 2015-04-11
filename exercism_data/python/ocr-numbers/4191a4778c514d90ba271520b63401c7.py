ZERO = [
    " _ ",
    "| |",
    "|_|",
    "   "
]

ONE = [
    "   ",
    "  |",
    "  |",
    "   "
]


def grid(num):

  if num == '0':
    return ZERO
  elif num == '1':
    return ONE
  else:
    raise ValueError


def number(num):

  if len(num) != 4:
    raise ValueError

  if len(set([ len(row) for row in num ])) > 1:
    raise ValueError

  if num == ZERO:
    return '0'
  elif num == ONE:
    return '1'
  else:
    return '?'  
