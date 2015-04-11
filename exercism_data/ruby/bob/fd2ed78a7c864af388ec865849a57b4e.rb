class Bob
  def hey(message)
    if silence? message
      'Fine. Be that way!'
    elsif shouting? message
      "Woah, chill out!"
    elsif question? message
      'Sure.'
    else
      'Whatever.'
    end
  end

protected
  def shouting?(message)
    message == message.upcase
  end

  def question?(message)
    message.end_with? '?'
  end

  def silence?(message)
    message.strip.empty?
  end
end
