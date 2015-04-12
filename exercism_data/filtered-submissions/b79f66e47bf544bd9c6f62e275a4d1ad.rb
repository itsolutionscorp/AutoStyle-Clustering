class Hamming
  def compute(strand_1, strand_2)
    first_strand = strand_1.split(//)
    second_strand = strand_2.split(//)
    hamming_distance = 0
    i = 0
    while i < first_strand.length
      if first_strand[i] != second_strand[i]
        hamming_distance += 1
        i+=1
      else
        i+=1
      end
    end
    return hamming_distance
  end
end
