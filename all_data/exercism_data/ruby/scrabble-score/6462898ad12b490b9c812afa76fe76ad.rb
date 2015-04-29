class Scrabble
  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @word = word.to_s
  end

  def score
    word.each_char.reduce(0) do |sum, char|
      sum + char_value(char)
    end
  end

  private

  attr_reader :word

  def char_value(char)
    case char.upcase
    when 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' then 1
    when 'D', 'G' then 2
    when 'B', 'C', 'M', 'P' then 3
    when 'F', 'H', 'V', 'W', 'Y' then 4
    when 'K' then 5
    when 'J', 'X' then 8
    when 'Q', 'Z' then 10
    else
      0
    end
  end
end
