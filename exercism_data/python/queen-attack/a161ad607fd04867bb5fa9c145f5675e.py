def board(first, second):

  check_position(first, second)

  ans = []
  row = []
  for i in range(8):
    for j in range(8):
      row.append('W') if first[0] == i and first[1] == j else row.append('B') if second[0] == i and second[1] == j else row.append('_')  
    ans.append("".join(row))
    row = []

  return ans


def can_attack(first, second):

  check_position(first, second)

  return first[0] == second[0] or first[1] == second[1] or abs(first[0] - second[0]) == abs(first[1] - second[1])


def check_position(first, second):

  if any([ e1 < 0 or e1 > 7 or e2 < 0 or e2 > 7 for e1 in first for e2 in second ]):
    raise ValueError

  if first == second:
    raise ValueError
