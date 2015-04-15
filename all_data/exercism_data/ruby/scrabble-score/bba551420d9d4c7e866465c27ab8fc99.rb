class Scrabble
  # Transformed to {letter => value}
  LETTER_VALUES = {
     1 => %w[A E I O U L N R S T],
     2 => %w[D G],
     3 => %w[B C M P],
     4 => %w[F H V W Y],
     5 => %w[K],
     8 => %w[J X],
    10 => %w[Q Z]
  }.each_with_object({}) do |(point, point_classes), new_hash|
    point_classes.each { |point_class| new_hash.merge!(point_class => point) }
  end

  attr_reader :string

  def self.score(string)
    new(string).score
  end

  def initialize(string)
    @string = string || ""
  end

  def score
    values.reduce(:+) || 0
  end

  private

  def values
    chars.map { |char| LETTER_VALUES[char] }
  end

  def chars
    string.upcase.gsub(/[^A-Z]/, '').chars.to_a
  end
end
