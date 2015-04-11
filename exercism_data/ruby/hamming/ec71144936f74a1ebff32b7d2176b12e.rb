class DNA

  def initialize(dna)
    @dna = dna
  end

  def hamming_distance(distance)
    count = 0
    @dna.length.times {|i| count+=1 if @dna[i]!=distance[i] and distance[i]}
    count
  end
end
