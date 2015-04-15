class Bob

  def hey(message)
    interpret_message(message)
  end

  private

    def interpret_message(message)
      MessagesInterpreter.new(message).give_answer
    end

end

class MessagesInterpreter

  attr_reader :message
  def initialize(message)
    @message = message
  end

  def give_answer
    message_types.each do |type|
      return type.new.answer if type.can_answer?(message)
    end    
  end

  private

    def message_types
      [Nothing, Yell, Question, Other]
    end


end

class Nothing

  def self.can_answer?(message)
    message.to_s.empty?
  end
  
  def answer
    "Fine. Be that way."
  end

end

class Question

  def self.can_answer?(message)
    message.end_with?("?")
  end
  
  def answer
    "Sure."
  end

end

class Yell

  def self.can_answer?(message)
    message.to_s.upcase == message
  end

  def answer
    "Woah, chill out!"
  end
  
end

class Other
  
  def self.can_answer?(message)
    true
  end

  def answer
    "Whatever." 
  end

end
