class Bob
  def hey(message)
    respond_to_message(message.strip)
  end

  private

  def respond_to_message(message)
    return 'Fine. Be that way!' if silent?(message)
    return 'Woah, chill out!' if shouting?(message)
    return 'Sure.' if question?(message)

    'Whatever.'
  end

  def silent?(message)
    message == ''
  end

  def shouting?(message)
    message.upcase == message
  end

  def question?(message)
    message[-1] == '?'
  end
end
