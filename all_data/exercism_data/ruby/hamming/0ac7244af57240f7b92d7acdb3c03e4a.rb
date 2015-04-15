class Hamming
  # returns the Hamming distance between two DNA strands
  def self.compute( seq1, seq2 )
    hamming_distance = 0
    max_length_comparable = [ seq1.length, seq2.length ].min
    
    for i in 0...max_length_comparable
      nucleotide1 = seq1[i]
      nucleotide2 = seq2[i]
      if nucleotide1 != nucleotide2
        hamming_distance += 1
      end
    end
    
    return hamming_distance
  end
end
