class String
  def score
    case self[0].upcase
    when 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'
      1
    when 'D', 'G'
      2
    when 'B', 'C', 'M', 'P'
      3
    when 'F', 'H', 'V', 'W', 'Y'
      4
    when 'K'
      5
    when 'J', 'X'
      8
    when 'Q', 'Z'
      10
    else
      0
    end
  end
end

class Scrabble
  def self.score string
    string.chars.inject(0) {  |s, c| s + c.score }
  end

  def initialize string
    @string = string || ''
  end

  def score
    self.class.score @string
  end
end
