#!/bin/ruby

# Bob.rb
# @author Count Jocular
#
# Bob is a lackadaisical teenager.
#

class Bob
  def hey( the_input )
    message = Message.new( the_input )
    return "Fine. Be that way!" if message.silence?
    return "Woah, chill out!"   if message.shouting?
    return "Sure."              if message.question?
    "Whatever."
  end
end

class Message
  def initialize( the_input )
    the_type = the_input.class
    raise "Input is #{the_type} (expected String)." if the_type != String
    @text = the_input
  end

  def silence?
    @text.gsub(' ', '') == ""
  end

  def shouting?
    @text.upcase == @text
  end

  def question?
    @text.end_with?('?')
  end
end
