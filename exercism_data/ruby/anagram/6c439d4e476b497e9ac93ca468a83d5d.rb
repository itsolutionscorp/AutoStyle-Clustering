class Anagram

  def initialize(word_to_watch)
    @word_to_match = word_to_watch
    @anagram_score = word_score(@word_to_match)
  end

  def match(possible_matches)
    anagrams = possible_matches.select do |word|
      anagram_of?(word)
    end
  end

private

  def anagram_of?(word)
    return false if same_as_anagram(word)
    word_score(word) == @anagram_score
  end

  def same_as_anagram(word)
    word.casecmp(@word_to_match) == 0
  end

  def word_score(word)
    word.downcase.split(//).sort
  end

end
