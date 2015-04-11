require_relative '../etl/etl'

class Scrabble

  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @letters = extract_letters(word)
  end

  def score
    scores_per_letter.inject(&:+) || 0
  end

  private

  def scores_per_letter
    letters.map { |letter| letter_scores[letter] }
  end

  def letter_scores
    @letter_scores ||= ETL.transform(legacy_letter_scores)
  end

  def legacy_letter_scores
    {
      1 => %w[A E I O U L N R S T],
      2 => %w[D G],
      3 => %w[B C M P],
      4 => %w[F H V W Y],
      5 => %w[K],
      8 => %w[J X],
      10 =>%w[Q Z],
    }
  end

  attr_reader :letters

  def extract_letters(word)
    word.to_s.strip.downcase.chars
  end
end
