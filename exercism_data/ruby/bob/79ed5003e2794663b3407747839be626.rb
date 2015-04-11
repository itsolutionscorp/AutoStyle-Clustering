class Bob

  def hey message
    message.extend(BobMessage)

    return "Fine. Be that way!" if message.silent?
    return "Woah, chill out!" if message.shouting?
    return "Sure." if message.asking?

    "Whatever."
  end

end

module BobMessage

  def silent?
    self.strip == ""
  end

  def shouting?
    self.upcase == self
  end

  def asking?
    self.end_with? "?"
  end

end
