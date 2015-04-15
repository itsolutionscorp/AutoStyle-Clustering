class Complement
  def self.of_dna(dna)
    dna.gsub(/[ACGT]/, 'A' => 'U', 'C' => 'G', 'G' => 'C', 'T' => 'A')
  end

  def self.of_rna(rna)
     rna.gsub(/[ACGU]/, 'A' => 'T', 'C' => 'G', 'G' => 'C', 'U' => 'A')
   end
end
