require_relative 'message'

class Bob
  
  def hey(speech)
    
    message = Message.new(speech)
    return "Fine. Be that way!" if message.empty?
    return "Woah, chill out!" if message.forceful?
    return "Sure." if message.question?
    return "Whatever."
  end
end
