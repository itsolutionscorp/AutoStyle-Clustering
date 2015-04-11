class Complement
  def self.of_dna(strand)
    dna_rules = {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
    }
    strand.gsub!(/./, dna_rules)
  end


  def self.of_rna(strand)
    rna_rules = {
        'G' => 'C',
        'C' => 'G',
        'U' => 'A',
        'A' => 'T'
    }
    strand.gsub!(/./, rna_rules)
  end

end
