class Complement

  DNA_COMPLEMENT = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U",
  }

  def self.of_dna(strand)
    strand.gsub(/[GCTA]/, DNA_COMPLEMENT)
  end

  def self.of_rna(strand)
    strand.gsub(/[CGAU]/, DNA_COMPLEMENT.invert)
  end
end
