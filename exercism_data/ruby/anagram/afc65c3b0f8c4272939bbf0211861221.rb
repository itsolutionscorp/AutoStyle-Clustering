class Anagram

  def initialize(test_word)
    @hashed_test_word = anagram_hash(test_word)
  end

  def match(possible_anagrams)
    possible_anagrams.select do |word|
      anagram_of_test_word?(word)
    end
  end

  private

  attr_reader :hashed_test_word

  def anagram_hash(word)
    word.chars.sort
  end

  def anagram_of_test_word?(word)
    hashed_test_word == anagram_hash(word)
  end

end
