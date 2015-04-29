class Scrabble
  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @word = valid_string(word)
    @tiles = word_tiles(@word)
    @mults = word_mults(@word)
    @tile_multiplier = {tile:1}
  end

  def score
    word_multiplier * word_base_value
  end

private
  attr_reader :tiles, :tile_multiplier, :mults
  def valid_string(word)
    word || ' '
  end

  def word_tiles(word)
    return [] unless word
    word.tr('^A-Za-z<^', '').upcase.chars
  end

  def word_mults(word)
    word.tr('^<', ' ')
  end

  def word_multiplier
    mults.split(' ').inject(1) {|prod,val| prod *= (val=='<' ? 2 : 3)}
  end

  def word_base_value
    tiles.inject(0) {|sum, tile| sum += value(tile) }
  end

  def value(tile)
    case tile
      when 'A','E','I','O','U',
           'L','N','R','S','T'  then adjusted_tile_value 1
      when 'D','G'              then adjusted_tile_value 2
      when 'B','C','M','P'      then adjusted_tile_value 3
      when 'F','H','V','W','Y'  then adjusted_tile_value 4
      when 'K'                  then adjusted_tile_value 5
      when 'J','X'              then adjusted_tile_value 8
      when 'Q','Z'              then adjusted_tile_value 10
      when '^'                  then tile_multiplier[:tile] += 1; 0
      else 0
    end
  end

  def adjusted_tile_value(val)
    mult = tile_multiplier[:tile]
    tile_multiplier[:tile] = 1
    val * mult
  end
end
