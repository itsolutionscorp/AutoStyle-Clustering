def hey(message):
  if(does_bob_hear_silence(message)):
    return "Fine. Be that way!";
  if(does_bob_hear_shouting(message)):
    return 'Woah, chill out!';
  if(does_bob_hear_a_question(message)):
    return  'Sure.';
  return 'Whatever.';

def does_bob_hear_silence(message):
  if(message.lstrip() == ''):
    return 1;
  else:
    return 0;

def does_bob_hear_shouting(message):
  if(message.isupper()):
    return 1;
  else:
    return 0;

def does_bob_hear_a_question(message):
  if(message.endswith('?')):
    return 1;
  else:
    return 0;
