class Message
  def initialize(text)
    @text = text.strip
  end
  
  def is_silent?
    @text == ''
  end

  def is_yelled?
    @text.upcase == @text and @text.downcase != @text
  end

  def is_question?
    @text.end_with?('?')
  end
end


class Bob
  
  REPLY_TO_SILENCE = 'Fine. Be that way!'
  REPLY_TO_YELLING = 'Woah, chill out!'
  REPLY_TO_QUESTION = 'Sure.'
  DEFAULT_REPLY = 'Whatever.'
  
  def hey(text)
    message = Message.new text
    case
    when message.is_silent?
      REPLY_TO_SILENCE
    when message.is_yelled?
      REPLY_TO_YELLING
    when message.is_question?
      REPLY_TO_QUESTION
    else
      DEFAULT_REPLY
    end
  end
end
