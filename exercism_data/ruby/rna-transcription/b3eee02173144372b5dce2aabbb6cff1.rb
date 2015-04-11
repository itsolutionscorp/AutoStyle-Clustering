class Complement
  def self.of_dna(dna)
    dna_hash = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }
    (0...(dna.length)).each do |n|
      dna[n] = dna_hash[dna[n]]
    end
    dna
  end

  def self.of_rna(rna)
    rna_hash = { 'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T' }
    (0...(rna.length)).each do |n|
      rna[n] = rna_hash[rna[n]]
    end
    rna
  end
end
