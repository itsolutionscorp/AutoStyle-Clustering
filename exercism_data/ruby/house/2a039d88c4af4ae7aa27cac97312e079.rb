class House

  def verse(n)
    "This is the " + additions_for_verse(n) + ".\n"
  end

  def verses(from, to)
    (from..to).map { |n| verse(n) + "\n" }.join
  end

  private

  def additions
    [
      :no_verse_zero,
      "house that Jack built",
      "malt that lay in the",
      "rat that ate the",
      "cat that killed the",
      "dog that worried the",
      "cow with the crumpled horn that tossed the",
      "maiden all forlorn that milked the",
      "man all tattered and torn that kissed the",
      "priest all shaven and shorn that married the",
      "rooster that crowed in the morn that woke the",
      "farmer sowing his corn that kept the",
      "horse and the hound and the horn that belonged to the"
    ]
  end

  def additions_for_verse(n)
    additions.slice(1..n).reverse.join(" ")
  end

end
