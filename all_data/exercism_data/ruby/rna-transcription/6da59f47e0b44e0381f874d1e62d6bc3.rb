class Complement
  def self.of_dna strand
    strand.gsub(/[GCTA]/, "G" => "C", "C" => "G", "T" => "A", "A" => "U")
  end

  def self.of_rna strand
    strand.gsub(/[GCAU]/, "C" => "G", "G" => "C", "A" => "T", "U" => "A")
  end
end
