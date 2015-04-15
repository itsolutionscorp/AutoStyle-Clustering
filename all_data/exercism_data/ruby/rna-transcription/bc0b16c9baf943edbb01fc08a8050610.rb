class Complement

  DNA_TO_RNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A", 
    "A" => "U" 
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(strand)
    strand.gsub(/[GCTA]/, DNA_TO_RNA)
  end

  def self.of_rna(strand)
    strand.gsub(/[CGAU]/, RNA_TO_DNA)
  end

end
