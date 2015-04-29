class Bob
  def hey phrase
    analyzer = PhraseAnalyzer.new phrase
    case analyzer.analyze
    when :shouting
      "Woah, chill out!"
    when :questioning
      "Sure."
    when :silence
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class PhraseAnalyzer
  def initialize phrase
    @phrase = phrase
  end

  def analyze
    if shouting?
      :shouting
    elsif questioning?
      :questioning
    elsif silence?
      :silence
    end
  end

  private

  def silence?
    @phrase.strip.empty?
  end

  def shouting?
    @phrase == @phrase.upcase && @phrase != @phrase.downcase
  end

  def questioning?
    @phrase.end_with? ??
  end
end
