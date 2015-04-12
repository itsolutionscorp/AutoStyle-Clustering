class Hamming
  def compute(strand_a, strand_b)
    strand_a.split('')
      .each_with_index
      .inject(0) do |distance, (nucleotide, index)|
        (nucleotide == strand_b[index]) ? (distance += 0) : (distance += 1)
      end
  end
end
