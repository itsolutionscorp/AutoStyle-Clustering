#!/usr/bin/env ruby

class Bob
  def hey(message_string)
    message = Message.new message_string
    if message.nothing?
      'Fine. Be that way!'
    elsif message.shouted?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  class Message
    def initialize(message)
      @message = message
    end
  
    def question?
      @message.end_with? "?"
    end

    def nothing?
      @message.nil? || @message.empty?
    end

    def shouted?
      @message.upcase == @message
    end
  end
end
