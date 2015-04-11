class Bob

  def hey(message)
    if not_messageing_anything?(message)
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

  def not_messageing_anything?(message)
    message.strip.empty?
  end

  def shouting?(message)
    message == message.upcase
  end

  def asking_a_question?(message)
    message.end_with?('?')
  end

end
