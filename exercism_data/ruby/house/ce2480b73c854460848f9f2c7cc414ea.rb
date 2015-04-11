class House
  DESCRIPTIONS = [
    'Jack built',
    'lay in',
    'ate',
    'killed',
    'worried',
    'tossed',
    'milked',
    'kissed',
    'married',
    'woke',
    'kept',
    'belonged to'
  ]
  NOUNS = [
    'house',
    'malt',
    'rat',
    'cat',
    'dog',
    'cow with the crumpled horn',
    'maiden all forlorn',
    'man all tattered and torn',
    'priest all shaven and shorn',
    'rooster that crowed in the morn',
    'farmer sowing his corn',
    'horse and the hound and the horn'
  ]

  def verse(n)
    story = 'This is'
    while n > 0
      n -= 1
      story += ' the ' + NOUNS[n] + ' that ' + DESCRIPTIONS[n]
    end

    story += ".\n"
  end

  def verses(start, finish)
    stories = ''
    start.upto(finish) do |i|
      stories << verse(i) + "\n"
    end
    stories
  end
end
