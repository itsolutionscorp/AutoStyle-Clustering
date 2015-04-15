class Bob

  def initialize    
  end

  def hey(message = nil)
    @message = message
    return 'Woah, chill out!' if yell?
    return "Sure." if question?
    return 'Fine. Be that way!' if say_nothing?
    return "Whatever."
  end
  
  def question?
    return true if @message[-1].eql?("?")
    return false
  end 
  
  def yell?
    return true unless @message.scan(/(GO|ZOMBIES|HELL|HATE|OUT|!!!)/).empty?    
    return false
  end
  
  def say_nothing?
    return true if @message.strip.empty?
    return false
  end
  
end
