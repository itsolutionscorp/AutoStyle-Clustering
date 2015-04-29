class Bob

  def hey(message)
    return 'Fine. Be that way!' if silence? message
    return "Woah, chill out!" if shouting? message
    return "Sure." if question? message

    "Whatever."
  end

  def silence?(message)
    message.strip.size == 0
  end

  def shouting?(message)
    capitalized_message = message.upcase
    message == capitalized_message && capitalized_message.match('[A-Z]')
  end

  def question?(message)
    message.end_with?('?')
  end
end
