class Hamming
  def compute(strand_a, strand_b)
    strand_a = strand_a.split('')
    strand_b = strand_b.split('')

    0 if strand_a == strand_b

    if strand_a.length == strand_b.length
      distance = 0
      strand_a.each_with_index do |nucleotide, index|
        distance += 1 unless nucleotide == strand_b[index]
      end
      distance
    end
  end
end
