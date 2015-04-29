class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def nothing?
    phrase.nil? or phrase.empty?
  end

  def yelling?
    phrase.upcase == phrase
  end

  def question?
    phrase.end_with? '?'
  end

  private
    attr_reader :phrase
end

class Bob
  def hey(phrase)
    heard = Phrase.new(phrase)
    return "Fine. Be that way!" if heard.nothing?
    return "Woah, chill out!"   if heard.yelling?
    return "Sure."              if heard.question?
    return "Whatever."
  end
end
