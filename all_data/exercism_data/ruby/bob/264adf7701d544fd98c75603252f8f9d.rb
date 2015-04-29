class Bob
  def hey message
    if empty_message? message
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
  def empty_message? message
    !message || message.strip.empty?
  end

  def shouting? message
    message.upcase == message
  end

  def question? message
    message.chars.last == '?'
  end
end
