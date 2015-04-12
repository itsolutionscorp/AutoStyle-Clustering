class Hamming
  def compute(strand1, strand2)

    difference = 0

    strand1.split("").each_with_index do |nucleotide, index|
      if strand2[index] && nucleotide != strand2[index]
        difference += 1
      end
    end

    return difference
  end
end
