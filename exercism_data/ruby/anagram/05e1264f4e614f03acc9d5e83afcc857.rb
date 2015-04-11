class Anagram
  class AnagramCandidate < String
    def normalize
      downcase
    end

    def anagram_of? other_candidate
      normalize.chars.sort.join == other_candidate.normalize.chars.sort.join unless normalize == other_candidate.normalize
    end
  end

  def initialize(word)
    @target = AnagramCandidate.new word
  end

  def match(candidate_words)
    candidate_words.map { |word| AnagramCandidate.new word }.select { |word| word.anagram_of? @target }
  end
end
