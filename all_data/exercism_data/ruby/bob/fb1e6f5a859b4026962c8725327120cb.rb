class Bob
  def hey message
    if empty? message
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
  def empty? message
    !message || message.strip.empty?
  end

  def shouting? message
    message.upcase == message
  end

  def question? message
    message.end_with? '?'
  end
end
