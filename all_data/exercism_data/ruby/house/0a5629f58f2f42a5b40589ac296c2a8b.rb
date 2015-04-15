class House

  def verse(number)
    'This is the ' + SUBJECTS.first(number).reverse.join(' the ') + ".\n"
  end

  def verses(first, last)
    (first..last).map {|number| verse(number) + "\n" }.join
  end

  SUBJECTS = [
    'house that Jack built',
    'malt that lay in',
    'rat that ate',
    'cat that killed',
    'dog that worried',
    'cow with the crumpled horn that tossed',
    'maiden all forlorn that milked',
    'man all tattered and torn that kissed',
    'priest all shaven and shorn that married',
    'rooster that crowed in the morn that woke',
    'farmer sowing his corn that kept',
    'horse and the hound and the horn that belonged to'
  ]
end
