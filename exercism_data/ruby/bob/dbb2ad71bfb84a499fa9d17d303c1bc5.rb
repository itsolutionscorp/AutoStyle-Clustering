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
    has_uppercase_letters? && !has_lowercase_letters?
  end

  def question?
    @phrase.end_with?("?")
  end

  def silent?
    @phrase.strip.empty?
  end

  private

  def has_uppercase_letters?
    @phrase.match(/[A-Z]+/)
  end

  def has_lowercase_letters?
    @phrase.match(/[a-z]+/)
  end
end
