class Complement

 Trans = { 'G' => 'C',
            'C' => 'G',
            'T' => 'A',
            'A' => 'U'
            }

  
  def self.of_dna(dna)
     # comp.chars { |dna| Trans[dna] }
     Trans[dna]
  end

  def self.of_rna(rna)
    Trans.key(rna) 
  end





end
