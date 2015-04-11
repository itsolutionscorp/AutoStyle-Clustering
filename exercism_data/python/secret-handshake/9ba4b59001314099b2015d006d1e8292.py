all_actions = ["wink", "double blink", "close your eyes", "jump"]
actions_to_code = {"wink": 1, "double blink": 10, "close your eyes": 100, "jump": 1000}


def handshake(h_code):

  if not isinstance(h_code, basestring):
    h_code = "{0:b}".format(h_code)
  if any([ digit != '0' and digit != '1' for digit in  h_code ]):
    h_code = '0'
  actions = []
  binary = list(reversed(h_code))
  if len(binary) <= len(all_actions):
    num_act = len(binary)
    reverse = False
  else:
    num_act = len(binary) - 1
    reverse = True
  for bit in range(num_act):
    if binary[bit] == '1':
      actions.append(all_actions[bit])

  return reverse and list(reversed(actions)) or actions


def code(actions):

  reverse = False
  old_index = -1
  total = 0 
  for action in actions:
    if action not in actions_to_code:
      total = 0
      break
    total += actions_to_code[action]
    index = all_actions.index(action)
    if index < old_index:
      reverse = True
    old_index = index
  if reverse:
    total += 10000

  return str(total)
