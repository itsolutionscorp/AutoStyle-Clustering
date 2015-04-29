# Class Complement

class Complement
  def self.of_dna dna_strand
    rna_strand = dna_strand.gsub(/[ACGT]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
  end

  def self.of_rna rna_strand
    dna_strand = rna_strand.gsub(/[ACGU]/, 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A')
  end
end
