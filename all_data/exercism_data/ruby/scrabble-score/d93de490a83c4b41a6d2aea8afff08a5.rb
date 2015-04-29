module ScrabbleLetterScore
  refine String do
    def score
      case self.upcase
      when *%w(A E I O U L N R S T)
        1
      when *%w(D G)
        2
      when *%w(B C M P)
        3
      when *%w(F H V W Y)
        4
      when *%w(K)
        5
      when *%w(J X)
        8
      when *%w(Q Z)
       10
     end
    end
  end
end

class Scrabble
  using ScrabbleLetterScore

  def initialize word
    @word = String(word).strip
  end

  attr_reader :word

  def score
    word.chars
        .map { |c| c.score }
        .reduce(0,:+)
  end

  def self.score word
    new(word).score
  end
end
