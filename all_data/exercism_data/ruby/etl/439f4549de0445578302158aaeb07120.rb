class ETL
  class TileSet
    def self.from_value_list value_list
      tile_set = TileSet.new
      value_list.each do |value, tiles|
        tile_set.add_tiles tiles, value
      end
      tile_set
    end
    
    attr_reader :values
    def initialize
      @values = {}
    end
    def add_tile tile, value
      @values[tile.downcase] = value
    end
    def add_tiles tiles, value
      tiles.each{|tile| add_tile tile, value }
    end
  end
  
  def self.transform legacy
    TileSet.from_value_list(legacy).values
  end
end
