class Bob

  RESPONSES = {
    silence: 'Fine. Be that way!',
    shouting: 'Woah, chill out!',
    question: 'Sure.',
    else: 'Whatever'
  }

  def hey(message)
    case categorize(message)
    when :silence
      'Fine. Be that way!'
    when :shouting
      'Woah, chill out!'
    when :question
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def categorize(message)
    if empty?(message)
      :silence
    elsif shouting?(message)
      :shouting
    elsif question?(message)
      :question
    else
      :else
    end
  end

  def empty?(message)
    message.strip.empty?
  end

  def shouting?(message)
    message.upcase == message
  end

  def question?(message)
    message.end_with?('?')
  end

end
