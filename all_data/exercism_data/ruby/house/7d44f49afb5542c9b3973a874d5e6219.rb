class House

  def verse num
    verses_starter + verse_parts.take(num).reverse.join
  end

  def verses first, last
    (first..last).map do |num|
      verse(num) + "\n"
    end.join
  end

  private

  def verses_starter
    "This is the"
  end

  def verse_parts
    [ " house that Jack built.\n",
      " malt that lay in the",
      " rat that ate the",
      " cat that killed the",
      " dog that worried the",
      " cow with the crumpled horn that tossed the",
      " maiden all forlorn that milked the",
      " man all tattered and torn that kissed the",
      " priest all shaven and shorn that married the",
      " rooster that crowed in the morn that woke the",
      " farmer sowing his corn that kept the",
      " horse and the hound and the horn that belonged to the"
    ]
  end

end
