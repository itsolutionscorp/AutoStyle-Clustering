class Complement
  def self.of_dna dna
    dna.gsub(/./, mapping)
  end

  def self.of_rna rna
    rna.gsub(/./, mapping.invert)
  end

  def self.mapping
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end
end
