module Complement
  DNA_Complements = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  extend self

  def of_dna(strand)
    strand.chars.map { |c| DNA_Complements[c] }.join
  end

  def of_rna(strand)
    strand.chars.map { |c| (DNA_Complements.invert)[c] }.join
  end

end
