module House
  PHRASES = [
    "the horse and the hound and the horn\nthat belonged to",
    "the farmer sowing his corn\nthat kept",
    "the rooster that crowed in the morn\nthat woke",
    "the priest all shaven and shorn\nthat married",
    "the man all tattered and torn\nthat kissed",
    "the maiden all forlorn\nthat milked",
    "the cow with the crumpled horn\nthat tossed",
    "the dog\nthat worried",
    "the cat\nthat killed",
    "the rat\nthat ate",
    "the malt\nthat lay in"
  ]

  def self.recite
    0.upto(PHRASES.length).map do |n|
      ['This is', *PHRASES.last(n), "the house that Jack built.\n"].join(' ')
    end.join("\n")
  end
end
