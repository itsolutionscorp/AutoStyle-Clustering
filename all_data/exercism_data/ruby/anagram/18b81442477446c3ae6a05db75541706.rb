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
    split_and_sort(word) == split_and_sort(@detector)
  end

  def split_and_sort(word)
    word.split("").sort
  end
end
