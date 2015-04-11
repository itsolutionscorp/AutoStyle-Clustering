require 'delegate'

class Anagram
  class AnagramCandidate < SimpleDelegator
    def normalize
      downcase
    end

    def to_comparable
      normalize.chars.sort.join
    end

    def anagram_of? other_candidate
      return false if normalize == other_candidate.normalize
      to_comparable == other_candidate.to_comparable
    end
  end

  def initialize(word)
    @target = AnagramCandidate.new word
  end

  def match(candidate_words)
    candidate_words.map { |word| AnagramCandidate.new word }.select { |word| word.anagram_of? @target }
  end
end
