class House

  OBJECTS_ACTIONS = [
    ['the house', 'Jack built'],
    ['the malt', 'lay in'],
    ['the rat', 'ate'],
    ['the cat', 'killed'],
    ['the dog', 'worried'],
    ['the cow with the crumpled horn', 'tossed'],
    ['the maiden all forlorn', 'milked'],
    ['the man all tattered and torn', 'kissed'],
    ['the priest all shaven and shorn', 'married'],
    ['the rooster that crowed in the morn', 'woke'],
    ['the farmer sowing his corn', 'kept'],
    ['the horse and the hound and the horn', 'belonged to']
  ]

  def templates
    @templates ||= (
      all = []

      OBJECTS_ACTIONS.map do |object, action|
        current = template_for(object, action)
        all.unshift(current)
        "This is #{all.join(' ')}.\n"
      end
    )
  end

  def template_for(object, action)
    "#{object} that #{action}"
  end

  def verse(number)
    templates[number - 1]
  end

  def verses(number, lines)
    templates[number - 1, lines].join("\n") + "\n"
  end
end
