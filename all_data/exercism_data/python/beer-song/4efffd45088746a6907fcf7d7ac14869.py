def song(first, last = 0):
  verses = []
  for i in range(first, last - 1, -1):
    verses.append(verse(i))
    verses.append('\n')
  return "".join(verses)

def verse(num):
  if num == 0:
    return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  old_num = str(num)
  new_num = str(num - 1) if num > 1 else "No more"
  old_bottle = "bottles" if num > 1 else "bottle"
  new_bottle = "bottles" if num > 2 or num == 1 else "bottle"
  take = "one" if num > 1 else "it"

  nth_verse = [old_num, " ", old_bottle, " of beer on the wall, ", old_num, " ", old_bottle, " of beer.\nTake ", take, " down and pass it around, ", new_num.lower(), " ", new_bottle, " of beer on the wall.\n"] 

  return "".join(nth_verse)
