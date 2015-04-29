class Scrabble
  attr_reader :letters

  def initialize(word)
    @letters = letters_from word
  end

  def score
    letters.map { |letter| score_for letter }.inject(0) { |sum, score| sum + score }
  end

  def self.score(word)
    new(word).score
  end

  private

  def letters_from(word)
    word.to_s.downcase.gsub(/[^a-z]/, '').chars
  end

  def score_for(letter)
    _, score = scores.detect { |(letters, _)| letters.include? letter }
    score
  end

  def scores
    [
      ['aeilnorstu', 1],
      ['dg',         2],
      ['bcmp',       3],
      ['fhvwy',      4],
      ['k',          5],
      ['jx',         8],
      ['qz',        10]
    ]
  end
end
