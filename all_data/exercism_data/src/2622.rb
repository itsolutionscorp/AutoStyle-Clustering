def compute(strand_1, strand_2)
    combined_strand = strand_1.chars.zip(strand_2.chars)

    combined_strand.count do |this_nucleotide, other_nucleotide|
      this_nucleotide != other_nucleotide
    end
  end
end