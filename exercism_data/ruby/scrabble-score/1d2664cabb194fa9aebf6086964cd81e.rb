class Scrabble
  SCORES = {
    1 => %w[A E I O U L N R S T],
    2 => %w[D G],
    3 => %w[B C M P],
    4 => %w[F H V W Y],
    5 => %w[K],
    8 => %w[J X],
    10 => %w[Q Z],
  }.each_with_object({}) do |(score, alphabets), score_hash|
    alphabets.each { |alphabet| score_hash[alphabet] = score }
  end

  def initialize(word)
    @word = parse word
  end

  def score
    @word.chars.reduce(0) { |total, letter| total + SCORES[letter.upcase] }
  end

  def self.score(word)
    new(word).score
  end

  private

  def parse(word)
    return "" unless word.is_a? String

    word.gsub(/[^\w+]/, '')
  end
end
