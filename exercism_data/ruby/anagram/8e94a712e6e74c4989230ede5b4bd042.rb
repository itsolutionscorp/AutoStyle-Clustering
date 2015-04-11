class Anagram
  def initialize(word)
    @word     = word.downcase
    @anagrams = build_anagrams
  end

  def match(candidates)
    candidates.select { |c| match?(c) }
  end

  private

  def build_anagrams
    permutations = @word.chars.to_a.permutation.map(&:join)
    permutations - [@word]
  end

  def match?(candidate)
    @anagrams.member?(candidate.downcase)
  end
end
