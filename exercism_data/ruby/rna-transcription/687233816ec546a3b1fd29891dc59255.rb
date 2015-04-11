module Complement

  def self.dna_complement_acids
    complement_acids = {
      "G" => "C",
      "C" => "G",
      "A" => "U",
      "T" => "A"
    }
  end

  def self.rna_complement_acids
    self.dna_complement_acids.invert
  end

  def self.of_dna(dna)
    self.create_complement(dna, self.dna_complement_acids)
  end

  def self.of_rna(rna)
    self.create_complement(rna, self.rna_complement_acids)
  end

  def self.create_complement(strand, complement_acids)
    complement = ''

    strand.chars.each do |acid|
      complement += complement_acids[acid]
    end

    complement
  end
end
