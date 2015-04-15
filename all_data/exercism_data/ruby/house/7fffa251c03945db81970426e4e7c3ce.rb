class House
  def verse(num)
    "This is #{verse_fragment(num)}.\n"
  end

  def verses(first, last)
    first.upto(last).map{ |n| verse(n) }.join("\n") + "\n"
  end

  private

  def verse_fragment(num)
    @verse_fragment ||= {}
    @verse_fragment[num] ||= VERSE_PARTS[0...num].reverse.join(' ')
  end

  VERSE_PARTS = [
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
    "the horse and the hound and the horn that belonged to"
  ]
  private_constant :VERSE_PARTS
end
