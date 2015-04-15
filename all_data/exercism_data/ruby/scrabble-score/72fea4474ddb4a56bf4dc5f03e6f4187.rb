class Scrabble
  ONES =   ->(tile){ %w[A E I O U L N R S T].include? tile }
  TWOS =   ->(tile){ %w[D G].include? tile }
  THREES = ->(tile){ %w[B C M P].include? tile }
  FOURS =  ->(tile){ %w[F H V W Y].include? tile }
  FIVES =  ->(tile){ %w[K].include? tile }
  EIGHTS = ->(tile){ %w[J X].include? tile }
  TENS =   ->(tile){ %w[Q Z].include? tile }

  def self.score(word)
    Scrabble.new(word).score
  end

  attr_accessor :word, :tiles
  def initialize(word)
    @word = word || ''
    @tiles = word ? get_tiles(word) : []
  end

  def score
   tiles.inject(0) {|sum, tile|sum += value(tile); sum}
  end

private
  def value(tile)
    case tile
      when ONES   then  1
      when TWOS   then  2
      when THREES then  3
      when FOURS  then  4
      when FIVES  then  5
      when EIGHTS then  8
      when TENS   then 10
      else 0
    end
  end

  def get_tiles(word)
    word.gsub(/[^A-Za-z]/,'').upcase.chars
  end
end
