class Bob
  def hey message
    if blank? message
      'Fine. Be that way!'
    elsif shouting? message
      'Woah, chill out!'
    elsif question? message
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def blank? message
    !message || message.strip.empty?
  end

  def shouting? message
    message.upcase == message
  end

  def question? message
    message.end_with? '?'
  end
end
