class House
  TEMPLATE = "This is %s.\n"

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
    TEMPLATE % noun_phrase(number)
  end

  def verses(first_verse, last_verse)
    first_verse.upto(last_verse).map { |number|
      verse(number) + "\n"
    }.join
  end

  private

  def noun_phrase(verse_number)
    THINGS.last(verse_number).join(" ")
  end
end