class Bob
  def hey(msg)
    msg = Message.new(msg)
    case
    when msg.silence?   then "Fine. Be that way."
    when msg.shout?     then "Woah, chill out!"
    when msg.question? then "Sure."
    else "Whatever."
    end
  end
end

class Message
  attr_reader :message
  def initialize(msg) @message = msg end
 
  def silence? 
    @message.nil? || @message.empty? 
  end
  def shout? 
    @message.upcase == @message 
  end
  def question? 
    @message.end_with?("?") 
  end
end
