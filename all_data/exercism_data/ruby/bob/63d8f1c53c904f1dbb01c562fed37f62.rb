class Bob
  
  def hey(message)
    if is_silent_treatment?(message)
      "Fine. Be that way!"
    elsif was_shouted?(message)
      "Woah, chill out!"
    elsif is_question?(message)
      "Sure."
    else
      'Whatever.'
    end
  end

  def is_silent_treatment?(message)
    !message || message.lstrip.empty?
  end

  def is_question?(message)
    message[-1] == '?'
  end

  def was_shouted?(message)
    message.upcase == message
  end

end
