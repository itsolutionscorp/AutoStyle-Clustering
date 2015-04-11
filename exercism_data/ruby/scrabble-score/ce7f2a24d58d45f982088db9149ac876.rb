class Scrabble
  def initialize word
    @word = word
  end

  def self.score word
    new(word).score
  end

  def score
    begin
      @word.chars.map(&:scrabble_value).inject(:+) || 0
    rescue
      0
    end
  end
end

class String
  def scrabble_value
    case self.downcase
    when 'a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't'
      1
    when 'd', 'g'
      2
    when 'b', 'c', 'm', 'p'
      3
    when 'f', 'h', 'v', 'w', 'y'
      4
    when 'k'
      5
    when 'j', 'x'
      6
    when 'q', 'z'
      10
    else
      0
    end
  end
end
