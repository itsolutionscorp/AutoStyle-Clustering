class Bob
  def hey(parent)
    case parent
    when silent? then react_to_silence
    when questioning? then react_to_question
    when yelling? then react_to_yelling
    else default_reaction
    end
  end

  private

  def questioning?
    ->(parent) { parent.end_with? '?' }
  end

  def yelling?
    ->(parent) { parent == parent.upcase }
  end

  def silent?
    ->(parent) { parent == '' or parent == nil }
  end

  def default_reaction
    'Whatever.'
  end

  def react_to_question
    'Sure.'
  end

  def react_to_yelling
    'Woah, chill out!'
  end

  def react_to_silence
    'Fine. Be that way.'
  end
end
