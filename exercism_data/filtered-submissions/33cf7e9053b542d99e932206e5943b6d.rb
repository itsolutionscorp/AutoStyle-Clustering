class Hamming
  def compute(strand_one, strand_two)
    hamming_distance = 0
    index = 0

    strand_one.split("").each do
      if strand_one[index] != strand_two[index] && strand_two[index] != nil
        hamming_distance +=1
      end
      index +=1
    end
    hamming_distance
  end
end
