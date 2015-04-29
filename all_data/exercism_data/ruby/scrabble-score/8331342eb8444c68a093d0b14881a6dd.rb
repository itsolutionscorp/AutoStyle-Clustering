class Scrabble
  def self.score string
    new(string).score
  end

  def initialize string
    @string = string || ''
  end

  def score
    @string.chars.inject(0) {  |s, l| s + letter_score(l) }
  end

  private

  def letter_score l
    case l.upcase
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
