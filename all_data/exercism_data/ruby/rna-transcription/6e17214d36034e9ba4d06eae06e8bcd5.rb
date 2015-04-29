class Complement
  # thanks http://stackoverflow.com/a/10989394/2317829
  # thanks http://stackoverflow.com/a/8132638/2317829

  RNA_TO_DNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(rna)
    rna.gsub(/[GCTA]/, RNA_TO_DNA)
  end

  def self.of_rna(dna)
    dna.gsub(/[CGAU]/, RNA_TO_DNA.invert)
  end
end
