class Bob

  def hey(message)
    if blank_message?(message)
      'Fine. Be that way.'
    elsif shouting?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def blank_message?(msg)
    msg.nil? || msg.empty?
  end

  def shouting?(msg)
    msg == msg.upcase
  end

  def question?(msg)
    msg.end_with?("?")
  end
end
