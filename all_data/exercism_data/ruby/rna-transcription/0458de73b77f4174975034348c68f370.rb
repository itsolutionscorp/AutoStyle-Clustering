class Complement
  def self.of_dna(dna)
    convert_nuc dna, DNA_MAPPING
  end

  def self.of_rna(rna)
    convert_nuc rna, RNA_MAPPING
  end

  private
  DNA_MAPPING = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  RNA_MAPPING = DNA_MAPPING.invert
  
  def self.convert_nuc(xna, mapping)
    xna.chars.map { |n| mapping[n] }.join
  end
end
