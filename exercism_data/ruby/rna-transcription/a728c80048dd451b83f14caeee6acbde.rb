class Complement
  
  COMPLEMENTS = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
  }
  
  def self.of_dna(rna_strand)
    rna_strand.split('').map do |x|
      COMPLEMENTS[x]
    end.join()
  end

  def self.of_rna(dna_strand)
    dna_strand.split('').map do |x|
      COMPLEMENTS.invert[x]
    end.join()
  end

end
