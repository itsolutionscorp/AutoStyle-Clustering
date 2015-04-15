class Complement
  def self.of_dna(nucleotide_sequence)
    complement_sequence_for(nucleotide_sequence, dna_to_rna_complement_map)
  end

  def self.of_rna(nucleotide_sequence)
    complement_sequence_for(nucleotide_sequence, rna_to_dna_complement_map)
  end

  def self.complement_sequence_for(nucleotide_sequence, complement_map)
    nucleotide_sequence.chars.reduce("") do |complement_sequence, nucleotide|
      complement_sequence << complement_map[nucleotide]
    end
  end

  def self.dna_to_rna_complement_map
    {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
  end

  def self.rna_to_dna_complement_map
    {
      "C" => "G",
      "G" => "C",
      "A" => "T",
      "U" => "A"
    }
  end

  private_class_method :dna_to_rna_complement_map, :rna_to_dna_complement_map, :complement_sequence_for
end
