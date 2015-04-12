class Bob
  def hey message
    if silence? message
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

  def shouting? message
    message.upcase == message
  end

  def question? message
    message.end_with? '?'
  end

  def silence? message
    message.strip.empty?
  end
end