class Message
  attr_reader :type

  def initialize(msg)
    @msg  = msg
    @type = find_msg_type
  end

  def find_msg_type
    is_empty? || is_shouting? || is_question? || :default
  end

  def is_empty?
    :empty    if @msg.to_s.empty?
  end

  def is_shouting?
    :shouting if @msg == @msg.upcase
  end

  def is_question?
    :question if @msg.end_with? "?"
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
        shouting:   "Woah, chill out!",
        question:   "Sure.",
        default:    "Whatever."
    }
  end
end
