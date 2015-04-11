class Bob
  def hey(message)
    greeting = Greeting.new(message)
    return 'Fine. Be that way!' if greeting.silence?
    return 'Woah, chill out!' if greeting.shouting_with_letters?
    return 'Sure.' if greeting.question?
    'Whatever.'
  end
end

class Greeting
  attr_accessor :message

  def initialize(message)
    self.message = message
  end

  def shouting_with_letters?
    shouting? && has_letters?
  end

  def question?
    self.message.end_with?('?')
  end

  def silence?
    self.message.strip.empty?
  end

  private

  def shouting?
    self.message.upcase == self.message
  end

  def has_letters?
    self.message =~ /[a-zA-Z]/
  end
end
