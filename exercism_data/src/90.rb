def compute dna_strand_a, dna_strand_b
    merged_dna = dna_strand_b.split('').zip dna_strand_b.split('')
    merged_dna.count { |joint| joint.uniq == 2 }
  end