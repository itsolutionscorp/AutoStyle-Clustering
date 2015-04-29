class Bob
  def hey(message)

    return "Fine. Be that way!" if is_silent(message)

    return "Woah, chill out!" if is_shouting(message)

    return "Sure." if is_question(message)

    "Whatever."
  end

  private
  def is_silent(message)
    message.strip.empty?
  end

  def is_shouting(message)
    message.upcase == message && message.upcase.match(/[A-Z]/)
  end

  def is_question(message)
    message.end_with?('?')
  end
end
