class Bob
  def hey(message)
    case
    when silence?(message)
      'Fine. Be that way!'
    when shouting?(message)
      'Whoa, chill out!'
    when asking?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def asking?(message)
    message.end_with? "?"
  end

  def shouting?(message)
    (message.upcase == message) and (message.downcase != message)
  end

  def silence?(message)
    message.strip.empty?
  end
end
