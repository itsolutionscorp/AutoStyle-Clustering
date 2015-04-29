class Anagram
  def initialize(word)
    @word = word
  end

  attr_reader :word

  def match(possible_anagrams)
    to_match = signature(word)
    possible_anagrams.select { |possible_match|
      signature(possible_match) == to_match
    }
  end

  private

  def signature(word)
    word.split("").sort.join
  end
end
