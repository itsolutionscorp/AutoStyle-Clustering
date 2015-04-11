class Bob

  def hey(message)
    @msg = message
    respond_as_teenager
  end

private

  def respond_as_teenager
    valid_response || 'Whatever.'
  end

  def valid_response
    silence_response || shout_response || question_response
  end

  def shout_response
    'Woah, chill out!' if message_is_shout?
  end

  def question_response
    'Sure.' if message_is_question?
  end

  def silence_response
    "Fine. Be that way!" if message_is_silence?
  end

  def message_is_shout?
    @msg[/[A-Z]/] && @msg.upcase === @msg
  end

  def message_is_question?
    @msg.end_with?('?')
  end

  def message_is_silence?
    !@msg[/\w/]
  end

end
