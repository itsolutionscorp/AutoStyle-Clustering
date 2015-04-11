class Bob
  def hey(msg)
    msg = Message.new(msg)
    case
    when msg.silent?   then "Fine. Be that way."
    when msg.loud?     then "Woah, chill out!"
    when msg.question? then "Sure."
    else "Whatever."
    end
  end
end

class Message
  attr_reader :message
  def initialize(msg); @message = msg end
 
  def silent?; @message.nil? || @message.empty? end
  def loud?;   @message.upcase == @message end
  def question?; @message.end_with?("?") end
end
