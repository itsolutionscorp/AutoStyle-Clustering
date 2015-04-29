class Bob
  def hey(incoming_sentence)
    case
    when silence?(incoming_sentence)
      return 'Fine. Be that way.'
    when shouting?(incoming_sentence)
      return 'Woah, chill out!'
    when question?(incoming_sentence)
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end

  private
  def shouting? sentence
    sentence.upcase == sentence
  end

  def question? sentence
    sentence.end_with?('?')
  end

  def silence? sentence
    sentence.to_s.empty?
  end
end
