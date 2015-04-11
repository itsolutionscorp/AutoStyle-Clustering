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
  attr_reader :body
  def initialize(msg) 
    @body = String(msg) 
  end
 
  def silence? 
    body.empty? 
  end
  def shout? 
    body.upcase == body
  end
  def question? 
    body.end_with?("?") 
  end
end
