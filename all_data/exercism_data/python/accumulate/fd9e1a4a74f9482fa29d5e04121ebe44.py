def accumulate(lst, f):
  if len(lst) == 0:
    return []
  else:
    return [f(lst[0])] + accumulate(lst[1:], f)
