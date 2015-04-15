class Anagram
  attr_reader :detector

  def initialize(detector)
    @detector = detector
  end

  def match(words)
    words.select do |word|
      anagram?(word)
    end
  end

  def anagram?(word)
    word.split("").sort == @detector.split("").sort
  end
end
