class Bob

  def hey(message)
    return "Fine. Be that way!" if is_empty?(message)
    return "Woah, chill out!" if is_all_caps?(message)
    return "Sure." if is_asking?(message)
    "Whatever."
  end

  private

  def is_empty?(message)
    message.match(/^\s*$/)
  end

  def is_all_caps?(message)
    message.gsub(/[\W\d]+/, '').match(/^[A-Z]+$/)
  end

  def is_asking?(message)
    return true if message[-1] == "?"
  end
end
