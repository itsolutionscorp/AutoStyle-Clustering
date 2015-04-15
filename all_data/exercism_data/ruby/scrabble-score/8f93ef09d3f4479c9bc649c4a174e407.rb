class Scrabble

  def initialize(word)
    @word = word.to_s.strip.downcase
  end

  def score
    @word.chars.map(&method(:value)).inject(:+).to_i
  end

  def self.score(word)
    new(word).score
  end

private

  def value(char)
    case char
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
      8
    when 'q', 'z'
      10
    end
  end

end
