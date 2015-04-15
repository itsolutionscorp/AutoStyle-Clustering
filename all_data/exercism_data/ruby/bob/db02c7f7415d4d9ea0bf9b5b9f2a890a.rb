module Teenager 
  def hey(input)
    return "Fine. Be that way!" if input.silent?
    return "Woah, chill out!" if input.shouting?
    return "Sure." if input.question?
    "Whatever."
  end
end

class Bob 
  include Teenager
end

class String
  def silent?
    self.strip.eql? ''
  end

  def shouting?
    self.eql? self.upcase
  end

  def question?
    self.end_with? '?'
  end
end
