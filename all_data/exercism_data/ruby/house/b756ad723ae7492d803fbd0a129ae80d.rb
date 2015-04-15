class House

  MIDDLE_QUALIFIERS = [
      'malt that lay in the ',
      'rat that ate the ',
      'cat that killed the ',
      'dog that worried the ',
      'cow with the crumpled horn that tossed the ',
      'maiden all forlorn that milked the ',
      'man all tattered and torn that kissed the ',
      'priest all shaven and shorn that married the ',
      'rooster that crowed in the morn that woke the ',
      'farmer sowing his corn that kept the ',
      'horse and the hound and the horn that belonged to the '
    ]

  def verse(num_qualifiers)
    start = "This is the "
    finish = "house that Jack built.\n"
    start + middle(num_qualifiers-1) + finish
  end

  def middle(middle_qualifiers)
    MIDDLE_QUALIFIERS.take(middle_qualifiers).reverse.join
  end

  def verses(start, finish)
    (start..finish).to_a.collect {|line| verse(line)+"\n"}.join
  end

end
