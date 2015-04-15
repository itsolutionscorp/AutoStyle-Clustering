class Bob  
  def messages
    [NoMessage.new,Shout.new,Question.new,InsignificantMessage.new]    
  end
  
  def hey(message)
    messages.find { |message_type| message_type.match?(message) }.response
  end  
end

class NoMessage
  def match?(message)
    message.nil? || message.empty?
  end
  
  def response
    "Fine. Be that way."
  end
end

class Question
  def match?(message)
    message.end_with?("?")
  end
  
  def response
    "Sure."
  end
end

class Shout
  def match?(message)
    message == message.upcase
  end
  
  def response
    "Woah, chill out!"
  end
end

class InsignificantMessage
  def match?(message)
    true
  end
  
  def response
    "Whatever."
  end
end
