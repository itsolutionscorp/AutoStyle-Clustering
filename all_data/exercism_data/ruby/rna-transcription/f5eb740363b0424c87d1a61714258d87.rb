module Complement
  @dna_rna = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def of_dna(strand)
    strand.chars.map { |el| @dna_rna[el] }.join
  end
end
