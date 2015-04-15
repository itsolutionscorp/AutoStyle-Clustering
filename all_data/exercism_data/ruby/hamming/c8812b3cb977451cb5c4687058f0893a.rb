class Hamming
  def self.compute(*strings)
    pair = new(strings)
    pair.distance
  end

  def initialize(strands)
    @strands = strands
  end

  def distance
    mutated_positions.count
  end

  private

  def min_length
    @strands.map(&:length).min
  end

  def positions
    (0...min_length)
  end

  def mutated_positions
    positions.select {|position| mutation_at? position}
  end

  def mutation_at?(position)
    @strands[0][position] != @strands[1][position]
  end
end
