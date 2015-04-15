class Complement
  DNA_TRANS = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_TRANS = DNA_TRANS.invert

  def self.of_dna(dna)
    dna.chars.map { |c| DNA_TRANS[c] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |c| RNA_TRANS[c] }.join
  end
end
