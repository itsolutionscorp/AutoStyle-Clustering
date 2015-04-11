class Bob
  def hey(message)
    response = responses.detect{|check,value| send(check, message.to_s)}
    response ? response.last : whatever
  end

  private
  def giving_silent_treatment?(message)
    message.strip.empty?
  end

  def screaming?(message)
    message == message.upcase
  end

  def questioning?(message)
    message.end_with?('?')
  end

  def whatever
    "Whatever."
  end

  def responses
    {
      giving_silent_treatment?: 'Fine. Be that way!',
      screaming?: 'Woah, chill out!',
      questioning?: 'Sure.',
    }
  end
end
