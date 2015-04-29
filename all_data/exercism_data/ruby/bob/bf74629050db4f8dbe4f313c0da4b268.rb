class Message
  attr_reader :type

  def initialize(msg)
    @msg  = msg
    @type = find_msg_type
  end

  def find_msg_type
    if is_empty?
      :empty
    elsif is_shouting?
      :shouting
    elsif is_question?
      :question
    else
      :default
    end
  end

  def is_empty?
    @msg.to_s.empty? || !@msg.match(/\w+/)
  end

  def is_shouting?
    @msg == @msg.upcase && @msg.match(/[A-Z]/)
  end

  def is_question?
    @msg.end_with? "?"
  end
end

module Responder
  def hey(msg)
    message = Message.new(msg)
    @responses[message.type]
  end
end

class Bob
  include Responder
  def initialize
    @responses = {
        empty:      "Fine. Be that way!",
        shouting:   "Whoa, chill out!",
        question:   "Sure.",
        default:    "Whatever."
    }
  end
end
