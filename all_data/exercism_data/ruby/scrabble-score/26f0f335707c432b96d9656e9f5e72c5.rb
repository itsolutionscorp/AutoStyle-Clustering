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

  attr_reader :letters, :word

  def initialize (word)
    @word = word
    @letters = parse_word unless invalid_word?
  end

  def score
    return 0 if invalid_word?
    @letters.reduce(0) {|sum,letter| sum + value_for(letter)}
  end

  def value_for (letter)
    VALUES.collect{|k,v| (v.include? letter) ? k : nil}.compact.join.to_i
  end

  def self.score (word)
    new(word).score
  end

  private

  def parse_word
    word.upcase.scan(/[A-Z]/)
  end

  def invalid_word?
    @word == nil
  end
end
