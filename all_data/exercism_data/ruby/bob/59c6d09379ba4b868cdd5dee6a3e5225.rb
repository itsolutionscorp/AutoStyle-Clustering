class Bob

  def hey(msg)
    return "Fine. Be that way." if msg.nil? or msg.size == 0
    return "Woah, chill out!" if msg == msg.upcase
    return "Sure." if msg.end_with?('?')
    "Whatever."
  end

end
