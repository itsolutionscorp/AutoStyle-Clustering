class Scrabble
  def initialize(tiles)
    @tiles = tiles
  end

  def score
    return 0 if invalid?
    @tiles.chars.map do |tile| 
      key = key_for_tile(tile.downcase)
      TILE_SCORES[key] 
    end.inject(:+)
  end

  def self.score(word)
    new(word).score
  end

  private

  def key_for_tile(tile)
    TILE_SCORES.keys.find{|collection| collection.include?(tile) }
  end

  def invalid?
    @tiles.to_s.strip.empty?
  end

  TILE_SCORES = {
    ['a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't'] => 1,
    ['d', 'g'] => 2,
    ['b', 'c', 'm', 'p'] => 3,
    ['f', 'h', 'v', 'w', 'y'] => 4,
    ['k'] => 5,
    ['j', 'x'] => 8,
    ['q', 'z'] => 10
  }
  
end
