class Complement
  @@dna_to_rna = {'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U'}

  def Complement.of_dna( dna )
    dna.chars.inject("") do |rna, nucleotide|
      rna.concat( @@dna_to_rna[nucleotide] )
    end
  end

  def Complement.of_rna( rna )
    rna.chars.inject("") do |dna, nucleotide|
      dna.concat( @@dna_to_rna.key(nucleotide) )
    end
  end
end
