class Anagram
  attr_reader :test_sorted_chars

  def initialize(word)
    @test_sorted_chars = sorted_chars_of word
  end

  def match(anagrams)
    anagrams.select do |word|
      sorted_chars_of(word) == test_sorted_chars
    end
  end

  private

  def sorted_chars_of(word)
    word.downcase.chars.sort
  end
end
