class Bob

  def hey(message)
    if hears_silence?(message)
      'Fine. Be that way!'
    elsif hears_yelling?(message)
      'Woah, chill out!'
    elsif hears_question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def hears_silence?(message)
    message.nil? || message.strip.empty?
  end

  def hears_yelling?(message)
    message == message.upcase
  end

  def hears_question?(message)
    message.end_with?('?')
  end

end
