class Hamming

  def compute strand1, strand2
    hamming_distance = 0
    strand1.split("").each_with_index do |item, index|
      if strand2[index]
        hamming_distance += strand1[index] == strand2[index] ? 0 : 1
      end
    end
    hamming_distance
  end
end
