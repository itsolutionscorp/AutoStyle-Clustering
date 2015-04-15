class Responder
  attr_accessor :speech

  def initialize(s)
    self.speech = s
  end

  def fine
    "Fine. Be that way!" if speech.to_s.empty?
  end

  def chill
    "Woah, chill out!" if speech == speech.upcase
  end

  def whatever
    "Whatever." if speech.end_with?(".")  or speech.end_with?("!")
  end

  def sure
    "Sure." if speech.end_with?("?")
  end

  def to_s
    fine || chill || whatever || sure
  end
end

class Bob

  def hey (speech)
    r = Responder.new(speech)
    r.to_s
  end
end
