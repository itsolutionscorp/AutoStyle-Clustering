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
      "",
      "",
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
    (additions.slice(0..n).reject(&:empty?).reverse.join(" ") + " ").lstrip
  end

end
