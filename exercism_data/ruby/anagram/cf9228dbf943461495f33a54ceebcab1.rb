class Anagram
  def initialize(reference)
    @reference = reference
  end

  def match(candidates)
    candidates.select do |candidate|
      AnagramCandidate.new(candidate).matches?(@reference)
    end
  end
end

class AnagramCandidate
  def initialize(candidate)
    @candidate = candidate
  end

  def matches?(reference)
    different_word?(reference) && anagram_for?(reference)
  end

  private

  def different_word?(reference)
    @candidate.downcase != reference.downcase
  end

  def anagram_for?(reference)
    normalize(@candidate) == normalize(reference)
  end

  def normalize(word)
    word.downcase.chars.sort
  end
end
