class Bob
  def statement? message
    message.end_with? '.'
  end

  def question? message
    message.end_with? '?'
  end

  def yelling? message
    message.strip.upcase == message
  end

  def silence? message
    message.nil? || message.strip.empty?
  end

  def hey( message )
    if silence? message
      'Fine. Be that way!'
    elsif yelling? message
      'Woah, chill out!'
    elsif question? message
      'Sure.'
    else
      'Whatever.'
    end
  end
end
