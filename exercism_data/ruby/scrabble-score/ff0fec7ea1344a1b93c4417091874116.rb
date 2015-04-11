class Scrabble
  def initialize(word)
    @word = word.nil? ? '' : word.strip
  end

  def score
    @word.chars.inject(0) { |total, letter| total += Tile.value(letter) }
  end

  def self.score(word)
    self.new(word).score
  end
end

class Tile
  VALUES = {
    1 =>  %w(A E I O U L N R S T),
    2 =>  %w(D G),
    3 =>  %w(B C M P),
    4 =>  %w(F H V W Y),
    5 =>  %w(K),
    8 =>  %w(J X),
    10 => %w(Q Z)
  }
  private_constant :VALUES

  def self.values
    VALUES.each_with_object({}) do |(value, letters), tiles|
      letters.each { |letter| tiles[letter] = value }
    end
  end

  def self.value(letter)
    values[letter.upcase]
  end
end
