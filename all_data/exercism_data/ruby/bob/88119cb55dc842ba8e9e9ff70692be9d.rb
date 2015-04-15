class Bob
  def hey(words)
    Response.to(words)
  end
end

class Response
  attr_reader :words

  def self.to(words)
    new(words).to_s
  end

  def initialize(words)
    @words = words
  end

  def to_s
    no_input || yelling || question || default
  end

  def no_input
    "Fine. Be that way." if words.to_s.empty?
  end

  def yelling
    "Woah, chill out!" if words.upcase == words
  end

  def question
    "Sure." if words.end_with?('?')
  end

  def default
    "Whatever."
  end
end
