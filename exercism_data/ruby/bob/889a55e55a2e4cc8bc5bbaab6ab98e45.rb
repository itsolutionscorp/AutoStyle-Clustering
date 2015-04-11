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
    message =~ /\?\Z/
  end

  def silence?(message)
    message.strip.length.zero?
  end
end
