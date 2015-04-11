class Scrabble

  TILE_VALUES = {
      "a" => 1, "b" => 3, "c" => 3, "d" => 2, "e" => 1,
      "f" => 4, "g" => 2, "h" => 4, "i" => 1, "j" => 8,
      "k" => 5, "l" => 1, "m" => 3, "n" => 1, "o" => 1,
      "p" => 3, "q" => 10, "r" => 1, "s" => 1, "t" => 1,
      "u" => 1, "v" => 4, "w" => 4, "x" => 8, "y" => 4,
      "z" => 10
  }

  def self.score(entry)
    score = 0
    (entry.chars.each_with_object(Hash.new(0)) { |char, summary| summary[char.downcase] += 1 }  ).each do |tile, count|
      score += TILE_VALUES.fetch(tile, 0) * count
    end
    score
  end


  def initialize(entry)
    @entry = entry || ""
  end

  def score
    self.class.score(@entry)
  end

end
