require_relative '../etl/etl'

class Scrabble
  def initialize(word)
    @word = word.to_s.downcase
    points = {1 => ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'],
    2 => ['D', 'G'],
    3 => ['B', 'C', 'M','P'],
    4 => ['F', 'H', 'V', 'W', 'Y'],
    5 => ['K'],
    8 => ['J', 'X'],
    10 => ['Q', 'Z']}
    @score = ETL.transform(points)
  end
  def score
    sum = 0
    @word.each_char do |letter|
      sum += @score[letter].to_i
    end
    sum
  end

  def self.score(word)
    new(word).score
  end
end
