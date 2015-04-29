elements = [ ("malt","lay"), ("rat","ate"), ("cat", "killed"), ("dog", "worried"), ("cow with the crumpled horn","tossed"), ("maiden all forlorn","milked"), ("man all tattered and torn", "kissed"), ("priest all shaven and shorn", "married"), ("rooster that crowed in the morn", "woke"), ("farmer sowing his corn", "kept"), ("horse and the hound and the horn", "belonged to") ]

def rhyme():

  res = []
  res.append("This is the house that Jack built.\n")

  for i in range(len(elements)):
    temp = ["This is the "]
    for j in range(i,-1,-1):
      temp.append(elements[j][0])
      temp.append('\n')
      temp.append("that ")
      temp.append(elements[j][1])
      if j == 0:
        temp.append(" in the house that Jack built.\n")
      else:
        temp.append(" the ")
    res.append("".join(temp))

  return '\n'.join(res).strip()
