class Message
  def initialize(msg)
    @msg = msg
  end

  def is_empty?
    @msg.nil? or @msg.empty?
  end

  def is_shouting?
   @msg == @msg.upcase
  end

  def is_question?
    @msg.end_with? "?"
  end
end

module Responder
 def hey(msg)
    message = Message.new(msg)
    case
      when message.is_empty?
        @responses[:empty]
      when message.is_shouting?
        @responses[:shouting]
      when message.is_question?
        @responses[:question]
      else
        @responses[:default]
    end
  end
end

class Bob
  include Responder
  def initialize
    @responses = {
        empty:      "Fine. Be that way!",
        shouting:   "Woah, chill out!",
        question:   "Sure.",
        default:    "Whatever."
    }
  end
end
