class House
  VERSES = ["house that Jack built.\n", "malt that lay in the ", "rat that ate the ", "cat that killed the ", "dog that worried the ", "cow with the crumpled horn that tossed the ", "maiden all forlorn that milked the ", "man all tattered and torn that kissed the ", "priest all shaven and shorn that married the ", "rooster that crowed in the morn that woke the ", "farmer sowing his corn that kept the ", "horse and the hound and the horn that belonged to the "]

  def initialize
    @rhyme = nil
  end

  def verse(count)
    @rhyme = "This is the "

    count.downto(1) {|c| @rhyme += VERSES[c-1] }

    @rhyme
  end

  def verses(lower, upper)
    total = ""
    lower.upto(upper) do |c|
      total << verse(c) +"\n"
    end
    total
  end
end
