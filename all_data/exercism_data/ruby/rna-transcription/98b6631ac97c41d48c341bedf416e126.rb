class Complement

  @dna_to_rna = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna_nucl)
    rna = ''
    for i in 0..dna_nucl.length-1
      rna << @dna_to_rna[dna_nucl[i]]
    end
    return rna
  end

  def self.of_rna(rna_nucl)
    dna = ''
    for i in 0..rna_nucl.length-1
      dna << @dna_to_rna.key(rna_nucl[i])
    end
    return dna
  end

end
