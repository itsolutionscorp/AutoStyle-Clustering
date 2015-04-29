class Bob
  attr_reader :phrase

  def hey(phrase)
    phrase = Phrase.new(phrase)

    if phrase.silence?
      "Fine. Be that way!"
    elsif phrase.yell?
      "Woah, chill out!"
    elsif phrase.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def silence?
    @phrase.nil? || @phrase.empty?
  end

  def yell?
    @phrase.match(/\p{Lower}/).nil?
  end
  
  def question?
    @phrase.end_with? "?"
  end
end
