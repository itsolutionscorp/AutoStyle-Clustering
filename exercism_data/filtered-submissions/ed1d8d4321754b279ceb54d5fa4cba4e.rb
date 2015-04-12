class Hamming
  def compute(strand_a, strand_b)
    distance = 0

    strand_a.split('').each_with_index do |nucleotide, index|
      distance += 1 unless nucleotide == strand_b[index]
    end

    distance
  end
end
