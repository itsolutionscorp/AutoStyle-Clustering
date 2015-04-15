require_relative 'tile'
class Scrabble
  MULT_MAX = 3

  def self.score(text)
    new(text).score
  end

  # Instance
  def initialize(text)
    @text = text || ''
    normalize
    extract_mults
    build_tiles
  end

  def score
    base_value * multiplier
  end

private
  attr_reader :text, :word, :mults, :tiles

  def normalize
    @word = text.tr('^A-Za-z^', '').upcase
  end

  def extract_mults
    @mults = text.scan(/<+/)
  end

  def build_tiles
    @tiles = tile_texts.map {|txt| Tile.new(txt) }
  end

  def tile_texts
    word.scan(/\^*[A-Z]/)
  end

  def base_value
    tiles.inject(0) {|sum, tile| sum += tile.value }
  end

  def multiplier
    mults.inject(1) {|prod, mult| prod *= val(mult)}
  end

  def val(mult)
    m = mult.length + 1
    m > MULT_MAX ? MULT_MAX : m
  end
end

class Tile
  LETTERS = {'A' => 1,'E' => 1,'I' => 1,'O' => 1,'U' => 1,
             'L' => 1,'N' => 1,'R' => 1,'S' => 1,'T' => 1,
             'D' => 2,'G' => 2,
             'B' => 3,'C' => 3,'M' => 3,'P' => 3,
             'F' => 4,'H' => 4,'V' => 4,'W' => 4,'Y' => 4,
             'K' => 5,
             'J' => 8,'X' => 8,
             'Q' => 10,'Z' => 10 }
  LETTERS.default = 0
  MULT_MAX = 3

  def initialize(text)
    parse text
  end

  def value
    return 0 unless letter
    LETTERS[letter.upcase] * multiplier
  end

private
  attr_reader :multchars, :letter

  def parse(text)
    /\A(?<multchars>\^*)(?<letter>[A-Za-z])\z/ =~ text
    @multchars, @letter = multchars, letter
  end

  def multiplier
    mult = multchars.length + 1
    mult > MULT_MAX  ?  MULT_MAX : mult
  end
end

#===============================
require 'minitest/autorun'
require_relative './word'

class ScrabbleTest < MiniTest::Test
  def test_calculates_word_value
    assert_equal 22, Scrabble.new('quirky').score
  end

  def test_calculates_value_of_word_with_multipliers
    assert_equal 132, Scrabble.new('qu<irk<<y').score
  end

  def test_ignores_xs_word_point_multiplier_characters
    assert_equal 18, Scrabble.new("st<<<reet").score
  end

  def test_forwards_letter_multipliers_with_tile_text
    assert_equal 7, Scrabble.new("stre^et").score
  end

  def test_empty_txt_scores_zero
    #skip
    assert_equal 0, Scrabble.new("").score
  end

  def test_whitespace_scores_zero
    #skip
    assert_equal 0, Scrabble.new(" \t\n").score
  end

  def test_nil_scores_zero
    #skip
    assert_equal 0, Scrabble.new(nil).score
  end
end

class TileTest < MiniTest::Test
  def test_returns_a_tile_value_for_lower_case_text
    assert_equal 1, Tile.new('a').value
  end

  def test_returns_a_tile_value_for_upper_case_text
    assert_equal 10, Tile.new('Q').value
  end

  def test_accepts_multiplier_characters
    assert_equal 8, Tile.new('^F').value
  end

  def test_limits_multiplier
    assert_equal 12, Tile.new('^^^^F').value
  end

  def test_returns_0_when_text_is_invalid
    assert_equal 0, Tile.new('^K tr@sh').value
  end
end
