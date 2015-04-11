class Bob

  def hey(sentence)
    analyzer = SentenceAnalyzer.new sentence
    return "Fine. Be that way!" if analyzer.nothing?
    return "Woah, chill out!" if analyzer.yelling?
    return "Sure." if analyzer.question?
    "Whatever."
  end

end

class SentenceAnalyzer

  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def nothing?
    sentence.strip.empty?
  end

  def yelling?
    sentence == sentence.upcase
  end

  def question?
    sentence.end_with? '?'
  end

end
