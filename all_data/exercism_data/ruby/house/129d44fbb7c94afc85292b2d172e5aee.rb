class House

  START = "This is"
  ENDING = ".\n"
  PHRASES = ['the house that Jack built',
            'the malt that lay in ',
            'the rat that ate ',
            'the cat that killed ',
            'the dog that worried ',
            'the cow with the crumpled horn that tossed ',
            'the maiden all forlorn that milked ',
            'the man all tattered and torn that kissed ',
            'the priest all shaven and shorn that married ',
            'the rooster that crowed in the morn that woke ',
            'the farmer sowing his corn that kept ',
            'the horse and the hound and the horn that belonged to '
            ]

  def verse n
    "#{START} #{body(n)}#{ENDING}"
  end

  def verses(n1, n2)
    n1.upto(n2).map { |n| body(n) }.join(' ')
  end

  def body n
    text = ''
    n -= 1
    until n < 0 
      text << PHRASES[n]
      n -= 1
    end
    text
  end

end
