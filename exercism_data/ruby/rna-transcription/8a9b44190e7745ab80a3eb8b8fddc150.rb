class Complement
  DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_TO_DNA = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }

  def self.of_dna(dna)
    new_nucleotide_string(dna, DNA_TO_RNA)
  end

  def self.of_rna(rna)
    new_nucleotide_string(rna, RNA_TO_DNA)
  end

  def self.new_nucleotide_string(original, complement)
    original.split(//).map { |nucleotide| complement[nucleotide] }.join
  end
end
