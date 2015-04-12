def compute dna_strand_a, dna_strand_b
    strands = [ dna_strand_a, dna_strand_b ].sort_by {|s| s.size }
    merged_dna = strands.first.chars.zip(strands.last.chars)
    merged_dna.count do |joint|
      joint.uniq.size == 2
    end
  end