class Scrabble
  VALUES = {
    1  => %w[A E I O U L N R S T],
    2  => %w[D G],
    3  => %w[B C M P],
    4  => %w[F H V W Y],
    5  => %w[K],
    8  => %w[J X],
    10 => %w[Q Z]
  }

  CHAR_VALUES = Hash.new(0)
  VALUES.each do |value, letters|
    letters.each {|l| CHAR_VALUES[l] = value}
  end

  attr_reader :letters, :word

  def initialize (word)
    @word = word || ''
  end

  def score
    @word.chars.reduce(0) {|sum,letter| sum + CHAR_VALUES[letter.upcase]}
  end

  def self.score (word)
    new(word).score
  end

end
