class Bob

  def hey(msg)
    return "Fine. Be that way!" if msg.gsub(/\s+/,'') == ""
    return "Woah, chill out!" if msg == msg.upcase
    return "Sure." if msg[-1,1] == "?"
    "Whatever."
  end

end
