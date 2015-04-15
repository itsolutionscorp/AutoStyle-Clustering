class Bob
  def hey(message)
    message = message.to_s
    responses.detect(default_response){|check,value| send(check, message)}.last
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

  def default_response
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
