class Whatever
  def self.matches?(message)
    true
  end

  def self.to_s() 'Whatever.' end
end

class Chillout
  def self.matches?(message)
    message.upcase == message && message.match(/[a-z]/i)
  end

  def self.to_s() 'Woah, chill out!'end
end

class Sure
  def self.matches?(message)
    message.end_with? '?'
  end

  def self.to_s() 'Sure.' end
end

class Fine
  def self.matches?(message)
    message.strip.empty?
  end

  def self.to_s() 'Fine. Be that way!' end
end

class Bob
  def hey(message)
    brain.find {|what_the_guy_just_said| what_the_guy_just_said.matches? message}.to_s
  end

  private

  def brain
    [Chillout, Sure, Fine, Whatever]
  end
end
