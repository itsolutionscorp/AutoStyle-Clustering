class Scrabble
  attr_reader :score

  Tiles = Hash.new 1
  %w{ d g       }.each { |l| Tiles[l] = 2 }
  %w{ b c m p   }.each { |l| Tiles[l] = 3 }
  %w{ f h v w y }.each { |l| Tiles[l] = 4 }
  %w{ k         }.each { |l| Tiles[l] = 5 }
  %w{ j x       }.each { |l| Tiles[l] = 8 }
  %w{ q z       }.each { |l| Tiles[l] = 10 }

  def self.score word
    word = word.downcase.chars.select { |c| /[a-z]/ =~ c }
    word.map { |w| Tiles[w]}.inject(0) { |sum,w| sum+w }
  end

  def initialize word
    @word = word.nil? ? "" : word
    @score = Scrabble.score(@word)
  end
end
