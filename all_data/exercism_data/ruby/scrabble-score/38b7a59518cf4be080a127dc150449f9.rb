require_relative "../etl/etl"

class Scrabble
  SCORES = ETL.transform({
                             0 => ["\t", "\n", " "],
                             1 => ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'],
                             2 => ['D', 'G'],
                             3 => ['B', 'C', 'M', 'P'],
                             4 => ['F', 'H', 'V', 'W', 'Y'],
                             5 => ['K'],
                             8 => ['J', 'X'],
                             10 => ['Q', 'Z']
                         })

  def initialize word
    @word = word || ""
  end

  def self.score word
    (new word).score
  end

  def score
    @word.chars.reduce(0) { |score, ltr|
      score + SCORES[ltr.downcase]
    }
  end
end
