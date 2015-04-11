class Bob
  def hey(message)
    message = message.to_s
    if silent_treatment?(message)
      "Fine. Be that way!"
    elsif shouted?(message)
      "Woah, chill out!"
    elsif question?(message)
      "Sure."
    else
      'Whatever.'
    end
  end

  def silent_treatment?(message)
    message.strip.empty?
  end

  def question?(message)
    message.end_with? '?'
  end

  def shouted?(message)
    message.upcase == message
  end

end
