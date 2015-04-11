class Bob
  def hey(message)
    case
    when silent?(message)
      'Fine. Be that way!'
    when screeming?(message)
      'Woah, chill out!'
    when asking?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silent?(message)
    !message || message.strip.empty?
  end

  def screeming?(message)
    message.upcase == message
  end

  def asking?(message)
    message.end_with?('?')
  end
end
