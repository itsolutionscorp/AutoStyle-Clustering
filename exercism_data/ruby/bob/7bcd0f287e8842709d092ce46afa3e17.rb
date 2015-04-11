class Bob
  attr_accessor :responses
  
  def initialize
    self.responses = { 
      :question_response => "Sure.", 
      :yell_response => "Woah, chill out!",
      :no_conversation_response => "Fine. Be that way!",
      :else_response => "Whatever."
    }
  end

  def hey(conversation)
    if self.empty?(conversation)
      self.responses[:no_conversation_response]
    elsif self.yelling?(conversation)
      self.responses[:yell_response]      
    elsif self.question?(conversation)
      self.responses[:question_response]
    else
      self.responses[:else_response]      
    end
  end

  def question?(conversation)
    return conversation.end_with?('?')
  end

  def empty?(conversation)
    return conversation.strip == ""
  end

  def yelling?(conversation)
    return conversation =~ /[A-Z]/ && conversation.upcase == conversation
  end

end
