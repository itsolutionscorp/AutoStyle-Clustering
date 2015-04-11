module ScrabbleScore
  refine String do
    def scrabble_score
      return each_char.reduce(0) { |a, e| a + e.scrabble_score } if length > 1
      case upcase
      when /[AEIOULNRST]/ then 1
      when /[DG]/         then 2
      when /[BCMP]/       then 3
      when /[FHVWY]/      then 4
      when /[K]/          then 5
      when /[JX]/         then 6
      when /[QZ]/         then 10
      else 0
      end
    end
  end
end

class Scrabble
  using ScrabbleScore

  def self.score(word)
    word.scrabble_score
  end

  def initialize(word)
    @word = String(word)
  end

  def score
    self.class.score(@word)
  end
end
