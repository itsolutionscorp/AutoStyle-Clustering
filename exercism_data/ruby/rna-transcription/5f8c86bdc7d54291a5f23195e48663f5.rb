module Complement
  DNA_COMPLEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  extend self

  def of_dna(strand)
    strand.chars.map { |c| DNA_COMPLEMENTS[c] }.join
  end

  def of_rna(strand)
    strand.chars.map { |c| DNA_COMPLEMENTS.key(c) }.join
  end

end
