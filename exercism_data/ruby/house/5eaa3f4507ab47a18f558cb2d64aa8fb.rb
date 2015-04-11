class House
  PHRASES = [
    "malt\nthat lay in the ",
    "rat\nthat ate the ",
    "cat\nthat killed the ",
    "dog\nthat worried the ",
    "cow with the crumpled horn\nthat tossed the ",
    "maiden all forlorn\nthat milked the ",
    "man all tattered and torn\nthat kissed the ",
    "priest all shaven and shorn\nthat married the ",
    "rooster that crowed in the morn\nthat woke the ",
    "farmer sowing his corn\nthat kept the ",
    "horse and the hound and the horn\nthat belonged to the "
  ]

  def self.recite
    (0..PHRASES.size).map do |num|
      "This is the #{PHRASES.take(num).reverse.join}" \
      "house that Jack built.\n"
    end.join("\n")
  end
end
