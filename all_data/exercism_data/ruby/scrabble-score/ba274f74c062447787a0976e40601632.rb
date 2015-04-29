class Scrabble
  def initialize(word)
    @word = word || ''
  end

  attr_reader :word

  def score
    each_letter.reduce(0) do |score, letter|
      score + letter_scores[letter]
    end
  end

  # Convenience shortcut
  def self.score(word)
    new(word).score
  end

  private

  def each_letter
    word.upcase.chars
  end

  LETTER_SCORE_GROUPS = {
    'AEIOULNRST' => 1,
    'DG'         => 2,
    'BCMP'       => 3,
    'FHVWY'      => 4,
    'K'          => 5,
    'JX'         => 8,
    'QZ'         => 10
  }

  UNKNOWN_LETTER_SCORE = 0

  def letter_scores
    @letter_scores ||=
      Hash.new(UNKNOWN_LETTER_SCORE).tap do |ls|
        LETTER_SCORE_GROUPS.each do |letters, score|
          letters.chars.each do |letter|
            ls[letter] = score
          end
        end
      end
  end
end
