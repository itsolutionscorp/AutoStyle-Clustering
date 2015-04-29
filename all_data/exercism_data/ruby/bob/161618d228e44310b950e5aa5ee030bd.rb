class Bob

  def hey(message)
    if is_silence? message
      'Fine. Be that way!'
    elsif is_shouting? message
      'Woah, chill out!'
    elsif is_a_question? message
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def is_silence?(message)
    message.strip.empty?
  end

  def is_a_question?(message)
    message.end_with?('?')
  end

  def is_shouting?(message)
    ! message.upcase!
  end
end
