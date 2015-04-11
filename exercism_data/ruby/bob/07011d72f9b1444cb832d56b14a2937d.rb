class Bob

  def hey(msg)
    return "Fine. Be that way." if silence?(msg)
    return "Woah, chill out!" if yelling?(msg)
    return "Sure." if asking?(msg)
    "Whatever."
  end

  private

  def silence?(msg)
    msg.nil? or msg.empty?
  end

  def yelling?(msg)
    !msg.match /[a-z]/
  end

  def asking?(msg)
    msg.end_with?("?")
  end

end
