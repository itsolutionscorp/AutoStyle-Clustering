class Scrabble
  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @tiles = get_tiles(word)
  end

  def score
   tiles.inject(0) {|sum, tile| sum += value(tile); sum}
  end

private
  attr_reader :tiles

  def value(tile)
    case tile
      when 'A','E','I','O','U','L','N','R','S','T' then  1
      when 'D','G'                                 then  2
      when 'B','C','M','P'                         then  3
      when 'F','H','V','W','Y'                     then  4
      when 'K'                                     then  5
      when 'J','X'                                 then  8
      when 'Q','Z'                                 then 10
      else 0
    end
  end

  def get_tiles(word)
    return [] unless word
    word.gsub(/[^A-Za-z]/,'').upcase.chars
  end
end
