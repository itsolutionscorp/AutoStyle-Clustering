class DNA

  def initialize(dna)
    @dna = dna.chars
  end

  def hamming_distance(distance)
    shorter_sequence = [@dna.length, distance.length].min
    shorter_sequence.times.count {|i| @dna[i]!=distance[i]}
  end
end
