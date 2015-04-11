class House

  STARTER = "This is the "
  PARTS = ["house", "malt that lay in", "rat that ate", "cat that killed",
    "dog that worried", "cow with the crumpled horn that tossed",
    "maiden all forlorn that milked", "man all tattered and torn that kissed",
    "priest all shaven and shorn that married",
    "rooster that crowed in the morn that woke",
    "farmer sowing his corn that kept",
    "horse and the hound and the horn that belonged to"]
  ENDER = " that Jack built.\n"
  
  def verse(count)
    STARTER + events(count) + ENDER
  end

  def verses(first, last)
    (first..last).map do |number|
      verse(number) + "\n"
    end.join
  end

  private

    def events(count)
      PARTS.take(count).reverse.join(" the ")
    end

end
