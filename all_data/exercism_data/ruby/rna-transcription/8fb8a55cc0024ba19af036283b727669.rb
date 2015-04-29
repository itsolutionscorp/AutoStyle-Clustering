class Complement

  def self.of_dna(dna_strand)
    dna_strand.gsub(/[GCTA]/,'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U')
  end

  def self.of_rna(rna_strand)
    rna_strand.gsub(/[CGAU]/,'C'=>'G', 'G'=>'C', 'A'=>'T', 'U'=>'A')
  end

end
