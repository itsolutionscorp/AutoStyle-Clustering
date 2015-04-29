class Anagram
  attr_accessor :target_histogram

  def initialize(target)
    self.target_histogram = letter_histogram target
  end

  def match(words)
    words.select {|word| check_histogram(word) }
  end

  private

  def check_histogram(word)
    compare_to_histogram(word).all? &:zero?
  end

  def compare_to_histogram(word)
    temp_histogram = target_histogram.clone
    word.each_char {|c| temp_histogram[c] -= 1 }
    temp_histogram.values
  end

  def letter_histogram(word)
    histogram = Hash.new(0)
    word.each_char {|c| histogram[c] += 1 }
    histogram
  end
end
