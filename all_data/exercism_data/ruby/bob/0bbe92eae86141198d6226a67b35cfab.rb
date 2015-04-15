class Bob
  SILENCE_RESPONSE = 'Fine. Be that way!'
  SHOUTING_RESPONSE = 'Woah, chill out!'
  QUESTION_RESPONSE = 'Sure.'
  GENERIC_RESPONSE = 'Whatever.'

  def hey(message)
    message.strip!

    if message.empty?
      return SILENCE_RESPONSE
    elsif message.upcase == message
      return SHOUTING_RESPONSE
    elsif message.end_with?('?')
      return QUESTION_RESPONSE
    else
      return GENERIC_RESPONSE
    end
  end
end
