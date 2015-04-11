class Bob
  def hey(message)

    case
    when silence?(message)
      'Fine. Be that way!'
    when yelling?(message)
      'Woah, chill out!'
    when question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(message)
    message.strip.empty?
  end

  def yelling?(message)
    message.upcase == message
  end

  def question?(message)
    message.end_with? '?'
  end
end
