class Scrabble
  ONE =   %w[A E I O U L N R S T]
  TWO =   %w[D G]
  THREE = %w[B C M P]
  FOUR =  %w[F H V W Y]
  FIVE =  %w[K]
  EIGHT = %w[J X]
  TEN =   %w[Q Z]

  def self.score(word)
    Scrabble.new(word).score
  end

  attr_accessor :word, :tiles
  def initialize(word)
    @word = word || ''
    @tiles = word ? get_tiles(word) : []
  end

  def score
   tiles.inject(0) {|sum, tile|sum += valu(tile); sum}
  end

private
  def valu(tile)
    return 1 if ONE.include? tile
    return 2 if TWO.include? tile
    return 3 if THREE.include? tile
    return 4 if FOUR.include? tile
    return 5 if FIVE.include? tile
    return 8 if EIGHT.include? tile
    return 10 if TEN.include? tile
  end

  def get_tiles(word)
    word.gsub(/[^A-Za-z]/,'').upcase.chars
  end
end
