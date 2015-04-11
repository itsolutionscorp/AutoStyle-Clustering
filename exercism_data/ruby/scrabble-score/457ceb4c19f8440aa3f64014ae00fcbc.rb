class Scrabble
  LETTER_VALUES = {['A', 'E', 'I', 'O', 'U', 'L', 
                    'N', 'R', 'S', 'T']               =>  1,
                   ['D', 'G']                         =>  2,
                   ['B', 'C', 'M', 'P']               =>  3,
                   ['F', 'H', 'V', 'W', 'Y']          =>  4,
                   ['K']                              =>  5,
                   ['J', 'X']                         =>  8,
                   ['Q', 'Z']                         =>  10
  }


  ## Initialize lookup
  CHAR_VALUE_MAP = Hash.new(0)
  LETTER_VALUES.each{|letters, value|
    letters.each{|l| CHAR_VALUE_MAP[l] = value}
  }

  def initialize(word)
    @word = word || ''
  end

  def score
    sum = @word.chars.map{|c| CHAR_VALUE_MAP[c.upcase]}.reduce(:+)
    return sum || 0
  end

  def self.score(word)
    new(word).score
  end
end
