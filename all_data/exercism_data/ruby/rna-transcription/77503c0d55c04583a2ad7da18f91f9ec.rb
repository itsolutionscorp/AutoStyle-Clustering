class Complement
  @@dna_to_rna = { ?C => ?G, ?G => ?C, ?T => ?A, ?A => ?U }
  @@rna_to_dna = { ?C => ?G, ?G => ?C, ?U => ?A, ?A => ?T }

  def self.of_dna dna
    dna.chars.map {|c| @@dna_to_rna[c]}.join
  end

  def self.of_rna rna
    rna.chars.map {|c| @@rna_to_dna[c]}.join
  end
end
