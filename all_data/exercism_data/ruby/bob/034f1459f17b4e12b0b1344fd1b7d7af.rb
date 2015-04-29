class Bob
  def hey(message)
    if yell?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    elsif nothing?(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def yell?(message)
    message == message.upcase && message != message.downcase
  end

  def question?(message)
    message.end_with? '?'
  end

  def nothing?(message)
    message.strip.empty?
  end
end
