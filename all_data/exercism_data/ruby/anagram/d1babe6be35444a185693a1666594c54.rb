class Anagram
  
  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    candidates.select {|candidate| is_anagram_of?(candidate)}
  end

  private

  def is_anagram_of?(candidate)
    downcased_candidate = candidate.downcase
    !@word.eql?(downcased_candidate) && permutations.include?(downcased_candidate)
  end

  def permutations
    @permutations ||= @word.chars.to_a.permutation.map(&:join)
  end
end
