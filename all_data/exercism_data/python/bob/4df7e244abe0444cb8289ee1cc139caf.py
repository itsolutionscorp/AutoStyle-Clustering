def hey(a):
    if(len(a.strip()) == 0):
      return 'Fine. Be that way!'
    elif(a.isupper()):
      return 'Whoa, chill out!'
    elif(a.strip()[-1] == '?'):
      return 'Sure.'
    else:
      return 'Whatever.'
    return
