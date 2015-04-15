class Anagram
  attr_accessor :target_histogram

  def initialize(target)
    self.target_histogram = letter_histogram target
  end

  def match(words)
    words.select {|word| anagram? word }
  end

  private

  def anagram?(word)
    letter_histogram(word) == target_histogram
  end

  def letter_histogram(word)
    histogram = Hash.new(0)
    word.each_char {|c| histogram[c] += 1 }
    histogram
  end
end
