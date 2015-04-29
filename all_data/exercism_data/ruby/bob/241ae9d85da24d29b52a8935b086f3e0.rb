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
    case conversation
      when empty?(conversation)
        return self.responses[:no_conversation_response]
      when question?(conversation)    
        return self.responses[:question_response]
      when yelling?(conversation)
        return self.responses[:yell_response]
      else
        return self.responses[:else_response]
    end
  end

  def question?(conversation)
    return conversation.end_with?('?')
  end

  def empty?(conversation)
    return conversation == ""
  end

  def yelling?(conversation)
    return conversation.upcase == conversation
  end

end
