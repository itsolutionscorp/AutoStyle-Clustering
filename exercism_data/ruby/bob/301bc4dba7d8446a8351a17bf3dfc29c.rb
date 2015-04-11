#!/usr/bin/env ruby

class Bob  
  def hey(message)
    message.extend(Teenager)
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
end

private
module Teenager
  def question?
    end_with?("?")
  end

  def nothing?
    nil? || empty?
  end

  def shouted?
    upcase == self
  end
end
