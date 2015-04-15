class Bob  
  def messages
    nomessage = NoMessage.new
    shout = Shout.new
    question = Question.new
    insignificant_message = InsignificantMessage.new
    
    [nomessage,shout,question,insignificant_message]    
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
    message.match(/\?$/)
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
