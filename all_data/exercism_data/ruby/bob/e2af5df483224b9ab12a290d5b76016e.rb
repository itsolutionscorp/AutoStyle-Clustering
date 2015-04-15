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
    message_types.find { |type| type.new(message).can_answer? }.answer
  end

  private

    def message_types
      [Nothing, Yell, Question, Other]
    end


end


class MessageType


  def self.answer
   raise "self.answer not implemented"
  end

  attr_reader :message
  def initialize(message)
    @message = message
  end

  def can_answer?
    raise "can_answer? not implemented"
  end

end


class Nothing < MessageType

  def self.answer
    "Fine. Be that way."
  end

  def can_answer?
    message.to_s.empty?
  end

end

class Question < MessageType

  def self.answer
    "Sure."
  end

  def can_answer?
    message.end_with?("?")
  end

end

class Yell < MessageType

  def self.answer
    "Woah, chill out!"
  end

  def can_answer?
    message.to_s.upcase == message
  end
  
end

class Other < MessageType
  
  def self.answer
    "Whatever." 
  end

  def can_answer?
    true
  end

end
