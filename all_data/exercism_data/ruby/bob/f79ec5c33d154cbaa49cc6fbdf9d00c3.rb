class Bob
  def hey(phrase)
    greeting = Greeting.new(phrase)
    return "Fine. Be that way!" if greeting.silent?
    return "Woah, chill out!" if greeting.shout?
    return "Sure." if greeting.question?
    "Whatever."
  end
end

class Greeting
  def initialize(phrase)
    @phrase = phrase
  end

  def shout?
    @phrase.match(/[A-Z]+/) && !@phrase.match(/[a-z]+/)
  end

  def question?
    @phrase.match(/\?\z/)
  end

  def silent?
    @phrase.strip.empty?
  end
end
