class Bob
  RESPOND_TO_SILENCE = 'Fine. Be that way!'
  RESPOND_TO_SHOUTING = 'Woah, chill out!'
  RESPOND_TO_QUESTION = 'Sure.'
  RESPOND_TO_OTHER = 'Whatever.'

  def hey message
    user_message = Message.new(message)
    if user_message.silence?
      RESPOND_TO_SILENCE
    elsif user_message.shout?
      RESPOND_TO_SHOUTING  
   elsif user_message.question?
      RESPOND_TO_QUESTION
    else
      RESPOND_TO_OTHER
    end
  end
end

class Message
  attr_accessor :message
  def initialize message
    @message = message
  end

  def shout?
      message == message.upcase
  end

  def question?
    message.end_with?('?')
  end

  def silence?
    message.strip.size == 0
  end
end
