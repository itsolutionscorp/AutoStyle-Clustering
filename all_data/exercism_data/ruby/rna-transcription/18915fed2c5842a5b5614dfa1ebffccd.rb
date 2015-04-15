module Complement
  module_function

  def of_dna(strand)
    strand.chars.map { |char| rna_complement(char) }.join
  end

  def of_rna(strand)
    strand.chars.map { |char| dna_complement(char) }.join
  end

  def dna_complement(rna)
    return 'G' if rna == 'C'
    return 'C' if rna == 'G'
    return 'T' if rna == 'A'
    return 'A' if rna == 'U'
  end

  def rna_complement(dna)
    return 'C' if dna == 'G'
    return 'G' if dna == 'C'
    return 'A' if dna == 'T'
    return 'U' if dna == 'A'
  end
end
