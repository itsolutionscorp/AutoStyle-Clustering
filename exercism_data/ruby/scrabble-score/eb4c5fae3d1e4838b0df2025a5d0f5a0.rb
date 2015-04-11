class Scrabble
  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @tiles = get_tiles(word)
    @mults = (word || ' ').tr('^<', ' ')
    @multiplier = {tile:1, word:1}
  end

  def score
    word_multiplier
    tiles.inject(0) {|sum, tile| sum += value(tile) } * word_multiplier #multiplier[:word]
  end

  def word_multiplier
    mults.split(' ').inject(1) {|prod,val| prod *= (val=='<' ? 2 : 3)}
  end

#private
  attr_reader :tiles, :multiplier, :mults
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
      when '^'                  then multiplier[:tile] += 1; 0
      when '<'                  then multiplier[:word] += 1; 0
      else 0
    end
  end

  def get_tiles(word)
    return [] unless word
    word.gsub(/[^A-Za-z<^]/,'').upcase.chars
  end

  def adjusted_tile_value(val)
    mult = multiplier[:tile]
    multiplier[:tile] = 1
    val * mult
  end
end

require 'minitest/autorun'
require_relative './scrabble'

class ScrabbleTest < MiniTest::Test
  def test_double_word_point_scoring
    assert_equal 12, Scrabble.score("<street")
  end

  def test_double_word_point_scoring_with_more_than_one_word_doubled_tile
    assert_equal 88, Scrabble.score("<quir<ky")
  end

  def test_triple_word_point_scoring
    assert_equal 18, Scrabble.score("<<street")
  end

  def test_mixed_double_and_triple_word_point_scoring_tiles
    assert_equal 132, Scrabble.score('qu<irk<<y')
  end

  def test_ignores_xs_word_point_multiplier_characters
    assert_equal 18, Scrabble.score("st<<<reet")
  end
end
