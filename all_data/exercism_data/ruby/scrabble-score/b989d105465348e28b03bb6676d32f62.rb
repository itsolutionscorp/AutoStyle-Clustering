class Scrabble
  SCORES = {
      %w(A E I O U L N R S T) => 1,
      %w(D G) => 2,
      %w(B C M P) => 3,
      %w(F H V W Y) => 4,
      %w(K) => 5,
      %w(J X) => 8,
      %w(Q Z) => 10,
  }

  def self.score(word)
    return 0 if word.nil?
    word.each_char.inject(0) do |score, letter|
      score + DEFAULT_SCORER.score_letter(letter.upcase)
    end
  end

  def initialize(word)
    @word = word
  end

  def score
    self.class.score(@word)
  end

  private
  class LetterScorer < Struct.new(:letter_scores)
    def score_letter(letter)
      letter_scores.fetch(letter, 0)
    end

    def self.build_from(map)
      new(build_letter_scores_map(map))
    end

    def self.build_letter_scores_map(map)
      map.each_with_object({}) do |(letters, score), letter_scores|
        letters.each do |letter|
          letter_scores[letter] = score
        end
      end
    end
  end

  DEFAULT_SCORER = LetterScorer.build_from(SCORES)
end
