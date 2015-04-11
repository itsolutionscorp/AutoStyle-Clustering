class House
  THINGS = [
    "the horse and the hound and the horn that belonged to",
    "the farmer sowing his corn that kept",
    "the rooster that crowed in the morn that woke",
    "the priest all shaven and shorn that married",
    "the man all tattered and torn that kissed",
    "the maiden all forlorn that milked",
    "the cow with the crumpled horn that tossed",
    "the dog that worried",
    "the cat that killed",
    "the rat that ate",
    "the malt that lay in",
    "the house that Jack built"
  ]

  def verse(number)
    "This is #{noun_phrase(number)}.\n"
  end

  def verses(first_verse, last_verse)
    first_verse.upto(last_verse).map { |number|
      verse(number) + "\n"
    }.join
  end

  private

  def noun_phrase(verse_number)
    return if verse_number.zero?
    this_thing = THINGS[-verse_number]
    more_things = noun_phrase(verse_number - 1)
    [this_thing, more_things].compact.join(" ")
  end
end
