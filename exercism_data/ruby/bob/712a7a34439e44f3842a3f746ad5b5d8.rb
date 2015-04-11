require 'delegate'
class Bob
  def hey(message)
    Responder.new(self).respond_to(message)
  end

  def shout_response
    'Woah, chill out!'
  end

  def question_response
    'Sure.'
  end

  def silence_response
    'Fine. Be that way!'
  end

  def default_response
    'Whatever.'
  end
end

class Responder < SimpleDelegator
  def respond_to(message)
    message = Message.new(message)
    if message.shouting?
      shout_response
    elsif message.question?
      question_response
    elsif message.silent?
      silence_response
    else
      default_response
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
