class String
  def to_a
    chars.to_a
  end
end

module Complement
  def self.dna_to_rna_hash
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.rna_to_dna_hash
    dna_to_rna_hash.invert
  end

  def self.of_dna(dna)
    dna.to_a.collect do |component|
      dna_to_rna_hash[component]
    end.join
  end

  def self.of_rna(rna)
    rna.to_a.collect do |component|
      rna_to_dna_hash[component]
    end.join
  end
end
