class Hamming
  def self.compute(dna_strand_1, dna_strand_2)
    dna_strand_1_array = dna_strand_1.split(//)
    dna_strand_2_array = dna_strand_2.split(//)

    length_shortest_strand = dna_strand_1.length
    length_shortest_strand = dna_strand_2.length if dna_strand_2.length < dna_strand_1.length

    i=0
    hamming_distance = 0

    while i < length_shortest_strand
      if dna_strand_1_array[i] != dna_strand_2_array[i]
        hamming_distance = hamming_distance+1
      end
      i+=1
    end

    hamming_distance
  end
end
