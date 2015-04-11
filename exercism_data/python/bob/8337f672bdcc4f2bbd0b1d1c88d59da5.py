import regex as re
#import re


def hey(what):
    rx = '|'.join([
        '(\p{Ll}+)'           #lowercase chunk
        ,'(\p{Lu}+)'          #uppercase chunk
        ,'([0-9]+)'           #numbers...
        ,'(\?\s*$)'           #is question
    ])

    
    uc = 0
    lc = 0
    has_num = False
    is_qu = False

    for match in re.finditer(rx, what):
      if match.group(1):
        lc += len( match.group(1) )
      
      if match.group(2):
        uc += len( match.group(2) )
      
      if match.group(3):
        has_num = True

      if match.group(4):
        is_qu = True
     

    # ok its a little silly that one lowercase character can defeat this but whatevs

    # if(uc / 3 > lc):
    #   return 'Whoa, chill out!'


    if lc == 0 and uc:
      return 'Whoa, chill out!'

    if lc == 0 and not has_num:
      return 'Fine. Be that way!'

    if is_qu:
      return 'Sure.'

    #else
    return "Whatever."
