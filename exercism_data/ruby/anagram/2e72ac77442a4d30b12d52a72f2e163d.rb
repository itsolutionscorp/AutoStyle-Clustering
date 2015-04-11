require 'delegate'

class Anagram
  def initialize(word)
    @target = AnagramCandidate.new word
  end

  def match(candidate_words)
    candidate_words.select { |word| AnagramCandidate.new(word) == @target }
  end

  class AnagramCandidate < SimpleDelegator
    def normalize
      downcase
    end

    def to_comparable
      normalize.chars.sort.join
    end

    def same_word?(other_candidate)
      normalize == other_candidate.normalize
    end

    def ==(other_candidate)
      return false if same_word? other_candidate
      to_comparable == other_candidate.to_comparable
    end
  end
end
