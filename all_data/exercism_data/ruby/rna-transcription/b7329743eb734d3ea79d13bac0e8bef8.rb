class Complement

  Trans = { 'G' => 'C',
            'C' => 'G',
            'T' => 'A',
            'A' => 'U'
            }


  def self.of_dna(dna)
    dna.chars  { |d| Trans[d] }
    # Trans[dna]
  end

  def self.of_rna(rna)
    Trans.key(rna)
  end

end
