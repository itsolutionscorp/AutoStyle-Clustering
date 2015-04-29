require 'delegate'
class Bob
  def hey(message)
    message = Message.new(message)
    if message.shouting?
      'Woah, chill out!'
    elsif message.question?
      "Sure."
    elsif message.silent?
      "Fine. Be that way!"
    else
      'Whatever.'
    end
  end
end

class Message < SimpleDelegator
  def shouting?
    self.upcase == self && self.downcase != self
  end

  def question?
    self.end_with?('?')
  end

  def silent?
    self.strip.empty?
  end
end
