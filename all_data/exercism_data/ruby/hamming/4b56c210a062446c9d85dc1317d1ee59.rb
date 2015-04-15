class DNA

  def initialize(strand)
    @dna_seq = strand
  end

  def hamming_distance(new_strand)
    count = 0
    c = 0

    @dna_seq.chars.each do
      if @dna_seq[c] != new_strand[c] && new_strand[c] != nil
        count += 1
      end
      c += 1
    end
    count
  end
end
