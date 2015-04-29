class Scrabble
  attr_reader :word

  SCORE_TABLE = {
    %w{ A E I O U L N R S T } => 1,
    %w{ D G }                 => 2,
    %w{ B C M P }             => 3,
    %w{ F H V W Y }           => 4,
    %w{ K }                   => 5,
    %w{ J X }                 => 8,
    %w{ Q Z }                 => 10
  }

  def initialize(word)
    @word = word.to_s.strip
  end

  def self.score(word)
    new(word).score
  end

  def score
    word.chars.reduce(0) { |sum, char| sum + character_score(char) }
  end

  private

  def character_score(char)
    SCORE_TABLE.select { |letters, _| letters.include?(char.upcase) }.values.first
  end
end
