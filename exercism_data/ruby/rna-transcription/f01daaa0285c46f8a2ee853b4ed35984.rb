class Complement

  def self.pairs
    { 
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.of_dna(dna)
    rna = ''

    dna.each_char do |c|
      rna += pairs[c.upcase]
    end

    rna
  end

  def self.of_rna(rna)
    dna = ''

    rna.each_char do |c|
      dna += pairs.key(c.upcase)
    end

    dna
  end
end
