def compute(dna_1, dna_2)
    dna_enum = dna_2.chars.each
    dna_1.chars.count { |c| c != dna_enum.next }
  end