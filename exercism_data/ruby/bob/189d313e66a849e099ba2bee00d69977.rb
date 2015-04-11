class Bob

  SHOUTING = ->(message) {
   /^[^a-z]+$/ =~ message && !(/^[^A-Z]+$/ =~ message)
  }
  QUESTION = ->(message) {
    /\?\Z/ =~ message
  }
  SILENCE = ->(message) {
    message.empty? || /\A\s+\Z/ =~ message
  }
  
  def hey(message)
    case message
    when SHOUTING then whoa
    when QUESTION then sure
    when SILENCE then fine
    else whatever
    end

  end
    
  def whatever; "Whatever."; end
  def fine;     "Fine. Be that way!"; end
  def sure;     "Sure."; end
  def whoa;     "Whoa, chill out!"; end
end
