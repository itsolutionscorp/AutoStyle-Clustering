class Bob
  def hey(message)
    if shouting message
      'Woah, chill out!'
    elsif question message
      'Sure.'
    elsif silent message
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def question(message)
    message.split('').last == "?"
  end

  def shouting(message)
    !message.strip.empty? && message == message.upcase
  end

  def silent(message)
    message.strip.empty?
  end
end
