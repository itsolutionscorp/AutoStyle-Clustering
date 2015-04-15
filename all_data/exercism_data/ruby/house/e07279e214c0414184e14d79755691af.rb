class House

  def verse number
    "This is #{lines(number).join(' ')}.\n"
  end

  def verses start, finish
    (start..finish)
      .map { |number| verse(number) + "\n" }
      .join
  end

  private

  def lines number_of_lines
    lines = [
      'the house that Jack built',
      'the malt that lay in',
      'the rat that ate',
      'the cat that killed',
      'the dog that worried',
      'the cow with the crumpled horn that tossed',
      'the maiden all forlorn that milked',
      'the man all tattered and torn that kissed',
      'the priest all shaven and shorn that married',
      'the rooster that crowed in the morn that woke',
      'the farmer sowing his corn that kept',
      'the horse and the hound and the horn that belonged to'
    ]

    lines[0...number_of_lines].reverse
  end

end
