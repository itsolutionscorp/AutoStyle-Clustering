class Bob

  def hey message
    return "Woah, chill out!" if message == message.upcase && !!(message =~ /[a-zA-Z]/)
    return "Fine. Be that way!" if message.delete(' ').empty?
    return "Sure." if message[-1] == "?"
    "Whatever."
  end

end
