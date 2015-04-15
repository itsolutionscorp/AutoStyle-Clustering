class Scrabble
  LETTERS = { %w(A E I O U L N R S T) => 1,
              %w(D G)                 => 2,
              %w(B C M P)             => 3,
              %w(F H V W Y)           => 4,
              %w(K)                   => 5,
              %w(J X)                 => 8,
              %w(Q Z)                 => 10 }

  def self.score str
    new(str).score
  end

  attr_reader :score

  def initialize str
    @score = str.nil? || str.gsub(/\s/, '').empty? ? 0 : get_score(str)
  end

  private

  def get_score str
    str.chars.reduce(0) do |a, e|
      a + LETTERS.find { |k, _v| k.include? e.upcase }.last
    end
  end
end
