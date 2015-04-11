class Complement
  HASH = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  def self.of_dna dna
    rna = ''
    (0...(dna.length)).each do |i|
      rna += HASH[dna[i]]
    end
    rna
  end

  def self.of_rna rna
    dna = ''
    (0...(rna.length)).each do |i|
      dna += HASH.key(rna[i])
    end
    dna
  end
end
