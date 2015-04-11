class Scrabble
  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @word = Word.new(word)
  end

  def score
    @word.sum_of_points
  end
end

class Word
  def initialize(word)
    @word = word
  end

  def sum_of_points
    return 0 if empty?
    points.inject(:+)
  end

  private

  def empty?
    !@word || @word.strip.empty?
  end

  def letters
    @word.strip.downcase.split('')
  end

  def points
    letters.map{|letter| Tile.new(letter).points}
  end

end

class Tile
  def initialize(letter)
    @letter = letter
  end

  def points
    point_assignments[@letter]
  end

  private

  def point_assignments
    {
      "a" => 1, "b" => 3, "c" => 3, "d" => 2, "e" => 1,
      "f" => 4, "g" => 2, "h" => 4, "i" => 1, "j" => 8,
      "k" => 5, "l" => 1, "m" => 3, "n" => 1, "o" => 1,
      "p" => 3, "q" => 10, "r" => 1, "s" => 1, "t" => 1,
      "u" => 1, "v" => 4, "w" => 4, "x" => 8, "y" => 4,
      "z" => 10
    }
  end

end
