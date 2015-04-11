class Scrabble
  class TileSet
    def self.from_value_list value_list
      value_list.each_with_object(TileSet.new) do |(value,tiles), tile_set|
        tile_set.add_tiles tiles, value
      end
    end
  
    attr_reader :tile_values
    def initialize tile_values = {}
      @tile_values = tile_values
    end
    def add_tile tile, value
      @tile_values[tile.downcase] = value
    end
    def add_tiles tiles, value
      tiles.each{|tile| add_tile tile, value }
    end
    
    def [] tile
      tile_values[tile] || 0
    end
  end
end

class Scrabble
  VALUE_LIST = {
    1 => %w(A E I O U L N R S T),
    2 => %w(D G),
    3 => %w(B C M P),
    4 => %w(F H V W Y),
    5 => %w(K),
    8 => %w(J X),
    10 => %w(Q Z)
  }
  TILE_SET = TileSet.from_value_list(VALUE_LIST)
  def self.score word
    Scrabble.new(word).score
  end
  
  def initialize word
    @word = word.to_s.downcase
  end
  
  def score
    @word.chars.reduce(0) {|sum, char| sum + TILE_SET[char]}
  end
end
