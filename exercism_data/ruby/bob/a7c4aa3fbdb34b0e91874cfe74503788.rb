#!/bin/ruby

class Bob
  def hey( the_input )
    message = Message.new( the_input )
    return 'Fine. Be that way!' if message.silence?
    return 'Woah, chill out!'   if message.shouting?
    return 'Sure.'              if message.question?
    'Whatever.'
  end
end

class Message < String
  def silence?
    self.strip == '' || self.empty?
  end

  def shouting?
    self.upcase == self
  end

  def question?
    self.end_with?('?')
  end
end
