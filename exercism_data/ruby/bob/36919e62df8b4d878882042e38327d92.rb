class Bob
  def hey(message)
    if nothing? message
      'Fine. Be that way!'
    elsif yelling? message
      'Woah, chill out!'
    elsif question? message
      'Sure.'
    else  # something
      'Whatever.'
    end
  end

private
  def question?(message)
    message.end_with? '?'
  end

  def yelling?(message)
    message == message.upcase
  end

  def nothing?(message)
    message.nil? || message.strip.empty?
  end
end
