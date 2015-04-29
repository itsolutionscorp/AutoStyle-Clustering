class Bob
  def hey(phrase)
    PhraseResponder.new(phrase).quip
  end
end

class PhraseResponder
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def quip
    if nil_or_empty?(phrase)
      "Fine. Be that way!"
    elsif includes_shouting?(phrase)
      "Woah, chill out!"
    elsif includes_non_shouting_question?(phrase)
      "Sure."
    else
      "Whatever."
    end
  end

  def nil_or_empty?(phrase)
    phrase.nil? || phrase.match(/^\s*$/)
  end

  def includes_shouting?(phrase)
    phrase == phrase.upcase
  end

  def includes_non_shouting_question?(phrase)
    phrase.match(/\?$/)
  end
end
