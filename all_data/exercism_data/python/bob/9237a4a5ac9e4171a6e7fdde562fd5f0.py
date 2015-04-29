class bob:
 
  def hey(self , message):
    if message.strip() == '' :
        return 'Fine. Be that way!'
    elif message.isupper():
        return 'Whoa, chill out!'
    if message[-1::1] == '?':
        return 'Sure.'
    return 'Whatever.'
