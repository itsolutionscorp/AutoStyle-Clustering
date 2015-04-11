numbers	= ["first","second","third","fourth","fifth","sixth","seventh","eighth","ninth","tenth","eleventh","twelfth"]

gifts	= [", twelve Drummers Drumming",", eleven Pipers Piping",", ten Lords-a-Leaping",", nine Ladies Dancing",", eight Maids-a-Milking",", seven Swans-a-Swimming",", six Geese-a-Laying",", five Gold Rings",", four Calling Birds",", three French Hens",", two Turtle Doves",", and a Partridge in a Pear Tree"]

def verse(N):
  cur_verse = "On the "
  cur_verse += numbers[N-1]
  cur_verse += " day of Christmas my true love gave to me"
  if N>1:
    for i in range(N):
      cur_verse += gifts[12-N+i]
  else:
    cur_verse += gifts[11].replace("and ","")
  cur_verse += '.\n'
  return cur_verse

def verses(N1,N2):
  out_verses = ""
  for i in range(N1,N2+1):
    out_verses += verse(i)
    out_verses += "\n"
  return out_verses
    

def sing():
  return verses(1,12)

if __name__ == "__main__":
  print verse(1)
  print verse(4)
  print verse(12)
  print verses(1,3)
