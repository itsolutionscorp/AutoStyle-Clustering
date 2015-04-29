class Bob

  def hey(message)
    @msg = message
    respond_as_teenager
  end

private

  def respond_as_teenager
    response = silence_response
    response ||= shout_response
    response ||= question_answer
    response ||= default_response
  end

  def default_response
    'Whatever.'
  end

  def shout_response
    'Woah, chill out!' if message_is_shout?
  end

  def question_answer
    'Sure.' if message_is_question?
  end

  def silence_response
    "Fine. Be that way!" if message_is_silence?
  end

  def message_is_shout?
    @msg[/[A-Z]/] && @msg.upcase === @msg
  end

  def message_is_question?
    @msg[/\?\z/]
  end

  def message_is_silence?
    !@msg[/\w/]
  end

end
