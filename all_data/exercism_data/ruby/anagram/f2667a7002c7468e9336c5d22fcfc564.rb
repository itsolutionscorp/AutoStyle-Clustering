class Anagram
  def initialize(word_to_match)
    @pattern_to_match = pattern(word_to_match)
    @downcase_match = word_to_match.downcase
  end

  def match(candidates)
    candidates.select do |c|
      pattern(c) == @pattern_to_match &&
      c.downcase != @downcase_match
    end
  end

  def pattern(word)
    word.downcase.chars.sort.join
  end
end
