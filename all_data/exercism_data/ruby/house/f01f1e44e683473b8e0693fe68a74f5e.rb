class House

  def verse(n)
    "This is the " + additions_for_verse(n) + "house that Jack built.\n"
  end

  def verses(from, to)
    (from..to).map { |n| verse(n) + "\n" }.join
  end

  private

  def additions
    [
      :no_verse_zero,
      :no_addition_for_verse_one,
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
    first_verse_with_addition = 2
    return "" unless n >= first_verse_with_addition
    return additions.slice(first_verse_with_addition..n).reverse.join(" ") + " "
  end

end
