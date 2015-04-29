class Bob
  def hey(message)
    case
    when (is_silence? message)
      'Fine. Be that way.'
    when (is_scream? message) 
      'Woah, chill out!'     
    when (is_question? message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def is_silence?(message)
    message.to_s.empty?
  end

  def is_scream?(message)
    message.upcase == message
  end

  def is_question?(message)
    message.end_with? '?'
  end

  private :is_silence?, :is_scream?, :is_question?
end
