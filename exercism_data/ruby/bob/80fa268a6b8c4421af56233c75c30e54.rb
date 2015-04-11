class Bob

  def hey(phrase)
    intonation = Intonation.new(phrase)
    if intonation.exclamatory?
      "Woah, chill out!"
    elsif intonation.inquisitive?
      "Sure."
    elsif intonation.muted?
      "Fine. Be that way!"
    else 
      "Whatever."
    end
  end

end

class Intonation
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.to_s
  end

  def exclamatory?
    !muted? && phrase == phrase.upcase
  end

  def inquisitive?
    phrase.end_with?('?')
  end

  def muted?
    phrase.empty?
  end

end
