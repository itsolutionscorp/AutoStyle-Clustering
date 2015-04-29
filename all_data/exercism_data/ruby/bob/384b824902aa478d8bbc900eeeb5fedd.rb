class Bob

  def hey(message)
    if says_nothing? message
      'Fine. Be that way.'
    elsif yelled? message
      'Woah, chill out!'
    elsif question? message
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def says_nothing?(message)
    message.nil? || message.empty?
  end

  def yelled?(message)
    message.upcase == message
  end

  def question?(message)
    message.end_with? '?'
  end

end
