class Anagram

  def initialize(word_to_watch)
    @word_to_match = word_to_watch
    @anagram_score = word_score(@word_to_match)
  end

  def match(possible_matches)
    matching_anagrams = possible_matches.select do |word|
      next if same_as_anagram(word)
      word_score(word) == @anagram_score
    end
  end

private

  def same_as_anagram(word)
    word.casecmp(@word_to_match) == 0
  end

  def word_score(word)
    word.downcase.split(//).sort
  end

end
