class Complement



  def self.of_dna(dna)
    self.new().of_dna(dna)
  end

  def self.of_rna(rna)
    self.new().of_rna(rna)
  end

  def of_dna(dna)
    dna_to_rna_complement_map = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    complement(dna, dna_to_rna_complement_map);
  end

  def of_rna(rna)
    rna_to_dna_complement_map = {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }

    complement(rna, rna_to_dna_complement_map);
  end

  private
  def complement(target, map)
    target.split('').map do |char|
      map[char]
    end.join
  end
end
