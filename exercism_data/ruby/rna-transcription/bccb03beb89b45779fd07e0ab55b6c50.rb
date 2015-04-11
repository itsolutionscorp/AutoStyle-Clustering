class Complement
  DNA_HASH = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }
  RNA_HASH = DNA_HASH.invert

  def self.of_dna(dna)
    (0...(dna.length)).each do |n|
      dna[n] = DNA_HASH[dna[n]]
    end
    dna
  end

  def self.of_rna(rna)
    (0...(rna.length)).each do |n|
      rna[n] = RNA_HASH[rna[n]]
    end
    rna
  end
end
