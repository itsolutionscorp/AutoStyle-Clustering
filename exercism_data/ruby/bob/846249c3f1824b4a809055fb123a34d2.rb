class Bob
  def hey phrase
    PhraseAnalyzer.new(phrase).response
  end
end

class PhraseAnalyzer
  attr_reader :phrase

  def initialize phrase
    @phrase = phrase
  end

  def response
    if silent?
      "Fine. Be that way!"
    elsif shouting?
      "Woah, chill out!"
    elsif questioning?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silent?
    phrase.strip.empty?
  end

  def shouting?
    phrase == phrase.upcase && phrase != phrase.downcase
  end

  def questioning?
    phrase.end_with? ??
  end
end
