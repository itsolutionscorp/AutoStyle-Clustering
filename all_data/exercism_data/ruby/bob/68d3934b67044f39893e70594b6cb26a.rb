class Bob
  def hey(speech)
    case speech_type(speech)
    when :shouting
      'Woah, chill out!'
    when :question
      'Sure.'
    when :nothing
      'Fine. Be that way!'
    else 
      'Whatever.'
    end
  end

private

  def speech_type(speech)
    return :nothing  if speech.strip.empty?
    return :shouting if speech.upcase == speech
    return :question if speech.end_with?('?')

    :whatever
  end
end
