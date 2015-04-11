class Bob
  def hey(message)
    return "Fine. Be that way!" if message.strip.empty?
    return "Woah, chill out!" if no_lowercase?(message) && message.match(/[A-Z]/)
    return "Sure." if message[-1] == "?"

    "Whatever."
  end

  private

  def no_lowercase?(message)
    !message.upcase!
  end
end
