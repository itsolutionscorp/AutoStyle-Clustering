class Bob
  def hey message
    if nothing_said?(message)
      'Fine. Be that way!'
    elsif shouting?(message)
      'Woah, chill out!'
    elsif asking_a_question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def nothing_said? message
    message.strip.empty?
  end

  def shouting? message
    message.upcase == message
  end

  def asking_a_question? message
    message.end_with? '?'
  end
end
