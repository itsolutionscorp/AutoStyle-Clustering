class Complement
  DNA_MAPPING = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  RNA_MAPPING = DNA_MAPPING.invert

  def self.of_dna(dna)
    dna.chars.map { |n| DNA_MAPPING[n] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |n| RNA_MAPPING[n] }.join
  end
end
