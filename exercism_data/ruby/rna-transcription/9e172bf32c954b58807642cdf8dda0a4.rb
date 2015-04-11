class Complement

  OF_DNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  OF_RNA = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }

  def self.of_dna(dna)
    rna = ''
    dna.each_char do |c|
      raise ArgumentError if !OF_DNA[c]
      rna += OF_DNA[c]
    end
    return rna
  end

  def self.of_rna(rna)
    dna = ''
    rna.each_char do |c|
      raise ArgumentError if !OF_RNA[c]
      dna += OF_RNA[c]
    end
    return dna
  end

end
