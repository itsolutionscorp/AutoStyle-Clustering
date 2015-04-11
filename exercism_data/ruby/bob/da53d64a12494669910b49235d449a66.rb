class Bob

  def hey(phrase)
    intonation = Intonation.new(phrase)
    case
    when intonation.exclamatory?
      "Woah, chill out!"
    when intonation.inquisitive?
      "Sure."
    when intonation.mute?
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
    phrase == phrase.upcase && !mute?
  end

  def inquisitive?
    phrase.end_with?('?')
  end

  def mute?
    phrase.empty?
  end

end
