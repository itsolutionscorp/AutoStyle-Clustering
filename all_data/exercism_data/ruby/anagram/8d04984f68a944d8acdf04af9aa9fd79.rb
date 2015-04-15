class Anagram
  def initialize anagramate
    @anagramate = anagramate
  end

  def match candidates
    candidates_without_exact_matches(candidates)
    .select { |candidate| candidate.downcase.chars.sort == @anagramate.downcase.chars.sort }
  end

  def candidates_without_exact_matches (candidates)
    candidates.reject{|candidate| candidate.downcase == @anagramate.downcase }
  end
end
