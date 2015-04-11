class Anagram
  attr_reader :test_word

  def initialize(word)
    @test_word = word.downcase
  end

  def match(anagrams)
    anagrams.select do |word|
      sorted_chars_of(word) == sorted_chars_of(test_word)
    end
  end

  private

  def sorted_chars_of(word)
    word.downcase.chars.sort
  end
end
