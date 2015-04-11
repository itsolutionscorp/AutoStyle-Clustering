class Complement
  RNA = { 'A' => 'U', 'C' => 'G', 'T' => 'A', 'G' => 'C'}

  def self.of_dna(strand)
    strand.gsub(/[ACGT]/, RNA)
  end

  def self.of_rna(strand)
    strand.gsub(/[UGAC]/, RNA.invert)
  end
end
