class Complement
  
  @comp = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U',
    }

  def self.of_dna(dna)
    rna = []
    dna.split('').each do |ntide|
      rna.push @comp[ntide]
    end
    rna.join
  end

  def self.of_rna(rna)
    dna = []
    rna.split('').each do |ntide|
      dna.push @comp.key ntide
    end
    dna.join
  end


end
