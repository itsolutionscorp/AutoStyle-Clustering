class Complement
  @rna_to_dna = {"C" => "G",
                 "G" => "C",
                 "U" => "A",
                 "A" => "T"}
  def self.of_dna(seq)
      seq.gsub(/[ACGT]/) {|g| @rna_to_dna.key(g)}
  end

  def self.of_rna(seq)
      seq.gsub(/[ACGU]/) {|g| @rna_to_dna[g]}
  end
end
