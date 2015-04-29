class Scrabble
  def initialize(word)
    @word = word
  end
  def self.points_map
   {
      1 => %W[A E I O U L N R S T],
      2 => %W[D G],
      3 => %W[B C M P],
      4 => %W[F H V W Y],
      5 => %W[K],
      8 => %W[J X],
      10 => %W[Q Z]
    }
  end

  def self.point_for(char)
    points_map.find { |k,v| v.include?(char) }.first
  end

  def score
    Scrabble.score(@word)
  end

   def self.score(word)
      points = 0
      return points if !word
      word.scan(/[a-zA-Z]/).each do |c|
        points += point_for(c.upcase)
      end
      points
  end
end
