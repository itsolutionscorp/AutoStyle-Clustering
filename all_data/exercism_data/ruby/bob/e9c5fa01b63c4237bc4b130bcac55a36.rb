class Bob
  def hey(message)
    greeting = Greeting.new(message)
    case
    when greeting.silence?
      'Fine. Be that way!'
    when greeting.shouting?
      'Woah, chill out!'
    when greeting.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Greeting
  attr_accessor :message

  def initialize(message)
    self.message = message
  end

  def shouting?
    uppercase? && has_letters?
  end

  def question?
    self.message.end_with?('?')
  end

  def silence?
    self.message.strip.empty?
  end

  private

  def uppercase?
    self.message.upcase == self.message
  end

  def has_letters?
    self.message =~ /[a-zA-Z]/
  end
end
