class House
  @@first_lines = [
    "This is the house that Jack built.",
    "This is the malt",
    "This is the rat",
    "This is the cat",
    "This is the dog",
    "This is the cow with the crumpled horn",
    "This is the maiden all forlorn",
    "This is the man all tattered and torn",
    "This is the priest all shaven and shorn",
    "This is the rooster that crowed in the morn",
    "This is the farmer sowing his corn",
    "This is the horse and the hound and the horn"]
  @@last_lines = [
    "\nthat belonged to the farmer sowing his corn",
    "\nthat kept the rooster that crowed in the morn",
    "\nthat woke the priest all shaven and shorn",
    "\nthat married the man all tattered and torn",
    "\nthat kissed the maiden all forlorn",
    "\nthat milked the cow with the crumpled horn",
    "\nthat tossed the dog",
    "\nthat worried the cat",
    "\nthat killed the rat",
    "\nthat ate the malt",
    "\nthat lay in the house that Jack built."]
  def House.recite
    (0...12).map{|i|
      @@first_lines[i] + ((11-i)...12).map{|j| @@last_lines[j]}.join
    }.join("\n\n") + "\n"
  end
end
