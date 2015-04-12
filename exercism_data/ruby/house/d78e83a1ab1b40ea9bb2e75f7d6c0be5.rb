class House
  VERSES = [
    "the house that Jack built",
    "the malt that lay in",
    "the rat that ate",
    "the cat that killed",
    "the dog that worried",
    "the cow with the crumpled horn that tossed",
    "the maiden all forlorn that milked",
    "the man all tattered and torn that kissed",
    "the priest all shaven and shorn that married",
    "the rooster that crowed in the morn that woke",
    "the farmer sowing his corn that kept",
    "the horse and the hound and the horn that belonged to",
  ]

  def verse(number)
    "This is #{step(number)}.\n"
  end

  def verses(start, stop)
    (start..stop).map { |i| "#{verse(i)}\n" }.join
  end

  private

  def step(i)
    [VERSES[i-1], step(i-1)].compact.join(' ') if i > 0
  end
end