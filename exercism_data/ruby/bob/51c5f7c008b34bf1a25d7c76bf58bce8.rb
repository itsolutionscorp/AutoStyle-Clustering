class Bob
  def hey(incoming_sentence)
    case
    when is_silence(incoming_sentence)
      return 'Fine. Be that way.'
    when is_shouting(incoming_sentence)
      return 'Woah, chill out!'
    when is_question(incoming_sentence)
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end

  private
  def is_shouting sentence
    sentence.upcase == sentence
  end

  def is_question sentence
    sentence.end_with?('?')
  end

  def is_silence sentence
    sentence == '' || sentence == nil
  end
end
