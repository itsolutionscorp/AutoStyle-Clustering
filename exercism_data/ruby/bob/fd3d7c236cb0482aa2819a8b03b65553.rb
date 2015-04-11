#!/usr/bin/env ruby

class Bob
  def hey(message)
    if message.nil? || message.empty?
      'Fine. Be that way!'
    elsif message.upcase == message
      'Woah, chill out!'
    elsif message.end_with?("?")
      'Sure.'
    else
      'Whatever.'
    end
  end
end
