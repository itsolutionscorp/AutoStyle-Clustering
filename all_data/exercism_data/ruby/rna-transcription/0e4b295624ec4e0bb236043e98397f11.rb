class Complement

  DNA_TO_RNA =  {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna_str)
    dna_str.gsub(/[#{DNA_TO_RNA.keys.join}]/, DNA_TO_RNA)
  end

  def self.of_rna(rna_str)
    rna_str.gsub(/[#{RNA_TO_DNA.keys.join}]/, RNA_TO_DNA)
  end

end
