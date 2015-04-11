class Bob
  def hey phrase
    PhraseAnalyzer.applicable_anaylizer(phrase).response
  end
end

class PhraseAnalyzer
  def self.applicable_anaylizer phrase
    analyzers = [SilentPhraseAnalyzer, ShoutingPhraseAnalyzer, QuestioningPhraseAnalyzer, PhraseAnalyzer]
    analyzers.find { |analyzer| analyzer.new(phrase).applicable? }.new phrase
  end

  attr_reader :phrase

  def initialize phrase
    @phrase = phrase
  end

  def applicable?
    true
  end

  def response
    "Whatever."
  end
end

class SilentPhraseAnalyzer < PhraseAnalyzer
  def applicable?
    phrase.strip.empty?
  end

  def response
    "Fine. Be that way!"
  end
end

class ShoutingPhraseAnalyzer < PhraseAnalyzer
  def applicable?
    phrase == phrase.upcase && phrase != phrase.downcase
  end

  def response
    "Woah, chill out!"
  end
end

class QuestioningPhraseAnalyzer < PhraseAnalyzer
  def applicable?
    phrase.end_with? ??
  end

  def response
    "Sure."
  end
end
