class DNA

  def initialize(dna)
    @dna = dna
  end

  def hamming_distance(distance)
    count = 0
    for i in (0...@dna.length) 
      count+=1 if @dna[i]!=distance[i] and distance[i]
    end
    count
  end
end
