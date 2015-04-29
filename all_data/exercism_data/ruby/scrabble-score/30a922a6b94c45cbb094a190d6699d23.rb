class Scrabble
  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @tiles = get_tiles(word)
    @multiplier = {letter:1, word:1}
  end

  def score
    tiles.inject(0) do |sum, tile|
      sum +=  value(tile)
    end * multiplier[:word]
  end

private
  attr_reader :tiles, :multiplier
  def value(tile)
    case tile
      when 'A','E','I','O','U',
           'L','N','R','S','T'  then real_letter_value 1
      when 'D','G'              then real_letter_value 2
      when 'B','C','M','P'      then real_letter_value 3
      when 'F','H','V','W','Y'  then real_letter_value 4
      when 'K'                  then real_letter_value 5
      when 'J','X'              then real_letter_value 8
      when 'Q','Z'              then real_letter_value 10
      when '^'                  then multiplier[:letter] += 1; 0
      when '<'                  then multiplier[:word] += 1;   0
      else 0
    end
  end

  def get_tiles(word)
    return [] unless word
    word.gsub(/[^A-Za-z^<]/,'').upcase.chars
  end

  def real_letter_value(val)
    mult = multiplier[:letter]
    multiplier[:letter] = 1
    val * mult
  end
end


# Tests for double/triple point letters and words
require 'minitest/autorun'
require_relative './scrabble'

class ScrabbleTest < MiniTest::Test
  def test_double_letter_point_scoring
    assert_equal 7, Scrabble.score("str^eet")
  end

  def test_double_letter_point_scoring_with_more_than_one_doubled_letter
    assert_equal 41, Scrabble.score("^quir^k^y")
  end

  def test_triple_letter_point_scoring
    assert_equal 8, Scrabble.score("st^^reet")
  end

  def test_double_word_point_scoring
    assert_equal 12, Scrabble.score("<street")
  end

  def test_double_word_point_scoring_with_more_than_one_word_doubled_tile
    assert_equal 66, Scrabble.score("<quir<ky")
  end

  def test_triple_word_point_scoring
    assert_equal 18, Scrabble.score("<<street")
  end
end
