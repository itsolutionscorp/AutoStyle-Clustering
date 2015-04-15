class Hamming
  def self.compute(first_strand,second_strand)
    pairs = first_strand.chars.zip(second_strand.chars)
    pairs.select { |nucleotide| nucleotide[0] != nucleotide[1] }.length
  end
end
