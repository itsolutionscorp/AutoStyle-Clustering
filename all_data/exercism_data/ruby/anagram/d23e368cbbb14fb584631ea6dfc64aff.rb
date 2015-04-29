class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(possible_anagrams)
    possible_anagrams.
      select { |word_being_compared| anagram_of?(word_being_compared) }
  end

  def anagram_of?(word_being_compared)
    raw_letters == self.class.new(word_being_compared).raw_letters
  end

  def raw_letters
    word.strip.downcase.chars.sort
  end
end
