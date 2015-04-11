class Bob

  def hey(phrase)
    intonation = Intonation.new(phrase)
    case
    when intonation.exclamatory?
      "Woah, chill out!"
    when intonation.inquisitive?
      "Sure."
    when intonation.muted?
      "Fine. Be that way!"
    else 
      "Whatever."
    end
  end

end

class Intonation
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.to_s.strip
  end

  def exclamatory?
    !muted? && phrase == phrase.upcase
  end

  def inquisitive?
    !exclamatory? && phrase.end_with?('?')
  end

  def muted?
    phrase.empty?
  end

end
