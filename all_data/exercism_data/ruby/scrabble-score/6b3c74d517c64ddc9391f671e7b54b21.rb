class Scrabble
  def initialize(tiles)
    @tiles = parse(tiles)
  end

  def score
    @tiles.chars.inject(0) do |sum, tile|
      sum += TILE_SCORES[tile]
    end
  end

  def self.score(word)
    new(word).score
  end

  private

  def parse(tiles)
    tiles.to_s.downcase.strip
  end


  TILE_SCORES = {
    'a' => 1, 'b' => 3, 'c' => 3, 'd' => 2, 'e' => 1,
    'f' => 4, 'g' => 2, 'h' => 4, 'i' => 1, 'j' => '8',
    'k' => 5, 'l' => 1, 'm' => 3, 'n' => 1, 'o' => 1,
    'p' => 3, 'q' => 10, 'r' => 1, 's' => 1, 't' => 1,
    'u' => 1, 'v' => 4, 'w' => 4, 'x' => 9, 'y' => 4, 'z' => 10
  }

end
