class Bob
  def hey(message)
    if silence?(message)
      'Fine. Be that way!'
    elsif shouting?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(message)
    message.strip.empty?
  end

  def shouting?(message)
    letters = message.scan(/[[:alpha:]]/)
    letters.any? and letters.all? { |c| c == c.upcase }
  end

  def question?(message)
    message.end_with?('?')
  end
end
