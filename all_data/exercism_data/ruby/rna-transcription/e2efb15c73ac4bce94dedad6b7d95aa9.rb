class Complement
  DNA_TO_RNA = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    raise ArgumentError if dna == "U"
    dna.chars.map { |char| DNA_TO_RNA[char] }.join
  end

  def self.of_rna(rna)
    raise ArgumentError if rna == "T"
    rna.chars.map { |char| RNA_TO_DNA[char] }.join
  end
end
