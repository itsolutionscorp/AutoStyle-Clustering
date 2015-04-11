class House

  LINES = {
    1 => "the house that Jack built.",
    2 => "the malt that lay in",
    3 => "the rat that ate",
    4 => "the cat that killed",
    5 => "the dog that worried",
    6 => "the cow with the crumpled horn that tossed",
    7 => "the maiden all forlorn that milked",
    8 => "the man all tattered and torn that kissed",
    9 => "the priest all shaven and shorn that married",
    10 => "the rooster that crowed in the morn that woke",
    11 => "the farmer sowing his corn that kept",
    12 => "the horse and the hound and the horn that belonged to"
  }

  def verse(n)
    "This is " + lines(n) + "\n" 
  end

  def verses(a, b)
    (a..b).inject("") do |verses, n|
      verses += verse(n) + "\n"
    end
  end

  private

  def lines(n)
    if n == 1
      return LINES[1]
    else
      return LINES[n] + " " + lines(n - 1)
    end
  end
end
