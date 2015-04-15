class Bob

  def hey(message)
    if silence?(message)
      answer(:silence)
    elsif shouting?(message)
      answer(:shouting)
    elsif asking?(message)
      answer(:asking)
    else
      answer(:talking)
    end
  end

  def silence?(message)
    message.to_s.strip.empty?
  end

  def shouting?(message)
    message == message.upcase
  end

  def asking?(message)
    message.end_with?('?')
  end

  def answer(to)
    case to
    when :silence
      'Fine. Be that way!'
    when :shouting
      'Woah, chill out!'
    when :asking
      'Sure.'
    when :talking
      'Whatever.'
    end
  end

end
