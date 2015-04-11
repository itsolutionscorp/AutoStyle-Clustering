class Anagram
  def initialize(word_to_match)
    @pattern_to_match = pattern(word_to_match)
    @original_word = word_to_match.downcase
  end

  def match(candidates)
    candidates.select do |c|
      matches_pattern?(c) && is_not_original_word?(c)
    end
  end

  def matches_pattern?(candidate)
    pattern(candidate) == @pattern_to_match
  end

  def is_not_original_word?(candidate)
    candidate.downcase != @original_word
  end

  def pattern(word)
    word.downcase.chars.sort.join
  end
end
