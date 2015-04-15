class Bob
  RESPONSES = {
   mute: "Fine. Be that way!",
   shout: "Woah, chill out!",
   query: "Sure.",
   any: "Whatever.",
  }
  def hey message
    _, response = RESPONSES.find {|type, _| self.send "is_#{type}?", message }
    response
  end
  
  private
  def is_any? message
    true
  end
  def is_mute? message
    !message || message.strip.empty?
  end
  def is_shout? message
    message.upcase == message
  end
  def is_query? message
    message.end_with? "?"
  end
end
