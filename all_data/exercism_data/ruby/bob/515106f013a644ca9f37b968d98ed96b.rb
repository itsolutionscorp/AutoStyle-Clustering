class Bob
  def hey(message)
    message = message.to_s
    responses.detect(whatever){|check,value| send(check, message)}.last
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
    -> {["Whatever."]}
  end

  def responses
    {
      giving_silent_treatment?: 'Fine. Be that way!',
      screaming?: 'Woah, chill out!',
      questioning?: 'Sure.',
    }
  end
end
