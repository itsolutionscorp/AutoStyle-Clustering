class Bob
  attr_reader :phrase

  def initialize(phrase = Phrase.new)
    @phrase = phrase
  end

  def hey(phrase)
    if @phrase.silence?(phrase)
      "Fine. Be that way!"
    elsif @phrase.yell?(phrase)
      "Woah, chill out!"
    elsif @phrase.question?(phrase)
      "Sure."
    else
      "Whatever."
    end
  end
end

class Phrase
  def silence?(phrase)
    phrase.nil? || phrase.empty?
  end

  def yell?(phrase)
    phrase.match(/\p{Lower}/).nil?
  end
  
  def question?(phrase)
    phrase.end_with? "?"
  end
end
