class Anagram

  def initialize(test_word)
    @test_word = test_word
  end

  def match(possible_anagrams)
    hashed_test_word = anagram_hash(test_word)
    possible_anagrams.select do |word|
      hashed_test_word == anagram_hash(word)
    end
  end

  private

  attr_reader :test_word

  def anagram_hash(word)
    word.chars.sort
  end

end
