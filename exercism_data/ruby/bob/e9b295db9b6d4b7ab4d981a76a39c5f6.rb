class Bob
  def hey(message)
    message = message.to_s

    if silence? message
      'Fine. Be that way!'
    elsif question? message
      'Sure.'
    elsif shouting? message
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def silence?(message)
    message.empty?
  end

  def shouting?(message)
    not silence? message and all_caps? message
  end

  def question?(message)
    not shouting? message and message.end_with? '?'
  end

  def all_caps?(message)
    message.upcase == message
  end
end
