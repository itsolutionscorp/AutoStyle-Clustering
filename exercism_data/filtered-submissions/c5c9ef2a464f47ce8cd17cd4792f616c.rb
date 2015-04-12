class Hamming
  def compute(dna_strand_1, dna_strand_2)
    if dna_strand_1.length == dna_strand_2.length
      first_strand = dna_strand_1.split(//)
      second_strand = dna_strand_2.split(//)
      first_strand.zip(second_strand).map { |a, b| a == b }.count(false)
    else
      p "strands are not equal size"
    end
  end
end
