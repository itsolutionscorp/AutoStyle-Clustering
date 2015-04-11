class Scrabble
  POINT_VALUES = {
    1  => %w(A E I O U L N R S T),
    2  => %w(D G),
    3  => %w(B C M P),
    4  => %w(F H V W Y),
    5  => %w(K),
    8  => %w(J X),
    10 => %w(Q Z)
  }.each_with_object({}) { |(k, letters), m|
    letters.each { |l| m[l] = k }
  }

  def initialize(word)
    word ||= ""
    @word = word.upcase.gsub(/[^A-Z]/i, '')
  end

  def score
    @word.chars.map{ |l| POINT_VALUES[l] }.reduce(0, :+)
  end

  def self.score(word)
    new(word).score
  end
end
