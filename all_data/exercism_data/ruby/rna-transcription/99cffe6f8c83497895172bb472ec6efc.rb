class Complement
  TO_RNA =  { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  TO_DNA = TO_RNA.invert

  def self.of_dna(dna)
    dna.gsub!(/#{TO_RNA.keys}/, TO_RNA)
  end


  def self.of_rna(rna)
    rna.gsub!(/#{TO_DNA.keys}/, TO_DNA)
  end
end
