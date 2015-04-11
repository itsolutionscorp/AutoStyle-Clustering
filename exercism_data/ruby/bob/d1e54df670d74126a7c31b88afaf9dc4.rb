class Bob
  def hey(message)
    return 'Fine. Be that way!' if message.nil? || message.empty?

    if shouting?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def shouting?(message)
    message.upcase == message
  end

  def question?(message)
    message[-1] == '?'
  end
end
