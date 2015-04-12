class Hamming
  def compute(strand1, strand2)
    strand1.each_char.with_index.reduce(0) do |mem, (nucleotide, index)|
      mem += (nucleotide == strand2[index] ? 0 : 1)
    end
  end
end
