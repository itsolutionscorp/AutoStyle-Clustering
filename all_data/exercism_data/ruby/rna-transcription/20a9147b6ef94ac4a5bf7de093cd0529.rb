class Complement

  def self.of_dna(dna_strand)
    trans = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
    dna_strand.gsub(/[GCTA]/, trans)
  end

  def self.of_rna(rna_strand)
    trans = {"C" => "G", "G" => "C", "A" => "T", "U" => "A"}
    rna_strand.gsub(/[CGAU]/, trans)
  end

end
