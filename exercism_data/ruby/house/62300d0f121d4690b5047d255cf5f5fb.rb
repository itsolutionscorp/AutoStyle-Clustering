class House

  STARTER = "This is the "
  ENDING = " that Jack built.\n"
  EVENTS = ["house", "malt that lay in", "rat that ate", "cat that killed",
    "dog that worried", "cow with the crumpled horn that tossed",
    "maiden all forlorn that milked", "man all tattered and torn that kissed",
    "priest all shaven and shorn that married",
    "rooster that crowed in the morn that woke",
    "farmer sowing his corn that kept",
    "horse and the hound and the horn that belonged to"]
  
  def verse(count)
    STARTER + prepare_events(count) + ENDING
  end

  def verses(count, increment_until_verse)
    (0...increment_until_verse).each_with_object([]) do |increment, rhymes|
      rhymes << verse(count + increment)
    end.join("\n") + "\n"
  end

  private

    def prepare_events(count)
      EVENTS.take(count).reverse.join(" the ")
    end

end
