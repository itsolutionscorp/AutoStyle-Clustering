class Bob

  def hey(message)
    answer(MessageType.new(message))
  end

  private

    def answer(message_type)
      case 
      when message_type.silent?
        answer_silence 
      when message_type.yell?
        answer_yell
      when message_type.question? 
        answer_question
      else
        default_answer
      end
    end

    def answer_question
      "Sure."
    end

    def answer_silence
      "Fine. Be that way."
    end

    def answer_yell
      "Woah, chill out!"
    end

    def default_answer
      "Whatever." 
    end

end

class MessageType
  
  attr_reader :message
  def initialize(message)
    @message = message.to_s
  end  

  def silent?
    message.strip.empty?
  end

  def question?
    message.end_with?("?")
  end

  def yell?
    message.upcase == message
  end

end
