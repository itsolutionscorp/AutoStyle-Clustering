class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def not_saying_anything?
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
    phrase = Phrase.new(phrase)
    return "Fine. Be that way!" if phrase.not_saying_anything?
    return "Woah, chill out!"   if phrase.yelling?
    return "Sure."              if phrase.question?
    return "Whatever."
  end
end
