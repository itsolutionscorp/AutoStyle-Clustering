#!/usr/bin/env ruby
# Don Cruse

class Bob
  def hey(msg)
    message = Message.new(msg)
    return "Fine. Be that way!" if message.silence?
    return "Woah, chill out!" if message.yelling?
    return "Sure." if message.question?
    "Whatever."
  end

  class Message
    def initialize(msg)
      @msg = msg.to_s
    end
    def question?
      !@msg.match(/.+\?$/).nil?
    end
    def yelling?
      @msg.upcase == @msg
    end
    def silence?
      @msg.empty?
    end
  end
end
