class Hamming
  def self.compute(first_strand, second_strand)
    
    hamming_distance = 0

    for i in 0..first_strand.length
        hamming_distance += 1 if first_strand[i] != second_strand[i]
    end

    return hamming_distance
  end
end
