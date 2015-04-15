class Anagram
  def initialize(reference)
    @reference = reference
  end

  def match(candidates)
    candidates.select do |candidate|
      AnagramCandidate.new(candidate).is_match_for(@reference)
    end
  end
end

class AnagramCandidate
  def initialize(candidate)
    @candidate = candidate
  end

  def is_match_for(reference)
    not_a_duplicate_for(reference) && is_an_anagram_for(reference)
  end

  private

  def not_a_duplicate_for(reference)
    @candidate.downcase != reference.downcase
  end

  def is_an_anagram_for(reference)
    normalize(@candidate) == normalize(reference)
  end

  def normalize(word)
    word.downcase.chars.sort.join
  end
end
