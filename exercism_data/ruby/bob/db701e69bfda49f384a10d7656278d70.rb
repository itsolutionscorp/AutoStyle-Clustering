class Bob
  def hey(message)
    return "Woah, chill out!" if all_caps?(message)
    return "Sure." if question?(message)
    return "Fine. Be that way!" if silence?(message)
    "Whatever."
  end

private
  def all_caps?(message)
    message =~ /\A([A-Z]|\W|\d)*\z/ && message =~ /[A-Z]/
  end

  def question?(message)
    message =~ /\A.*\?\z/
  end

  def silence?(message)
    message.nil? || message =~ /\A\s*\z/
  end
end
