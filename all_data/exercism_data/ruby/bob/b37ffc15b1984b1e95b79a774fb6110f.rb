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
  class Message < String
    def question?
      end_with? "?"
    end

    def nothing?
      nil? || empty?
    end

    def shouted?
      upcase == self
    end
  end
end
