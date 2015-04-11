class Bob
  def hey(communiqué)
    case
    when silence?(communiqué)
      return 'Fine. Be that way.'
    when shouting?(communiqué)
      'Woah, chill out!'
    when question?(communiqué)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def shouting?(sentence)
    sentence.upcase == sentence
  end

  def question?(sentence)
    sentence.end_with?('?')
  end

  def silence? (sentence)
    sentence.to_s.empty?
  end
end
