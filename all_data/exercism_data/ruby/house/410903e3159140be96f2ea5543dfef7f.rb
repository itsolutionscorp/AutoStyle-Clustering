class House
  PHRASES = [
    "horse and the hound and the horn\nthat belonged to the ",
    "farmer sowing his corn\nthat kept the ",
    "rooster that crowed in the morn\nthat woke the ",
    "priest all shaven and shorn\nthat married the ",
    "man all tattered and torn\nthat kissed the ",
    "maiden all forlorn\nthat milked the ",
    "cow with the crumpled horn\nthat tossed the ",
    "dog\nthat worried the ",
    "cat\nthat killed the ",
    "rat\nthat ate the ",
    "malt\nthat lay in the ",
    "house that Jack built.\n"
  ]

  def self.recite(num = 1)
    next_verse = "\n#{recite(num + 1)}" unless num >= PHRASES.size

    "This is the #{PHRASES.last(num).join}#{next_verse}"
  end
end
