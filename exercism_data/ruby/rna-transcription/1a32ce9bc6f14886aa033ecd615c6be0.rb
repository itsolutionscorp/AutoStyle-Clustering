class Complement
  DNA_RNA_COMPLEMENT = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(strand)
    self.complement(strand, dna_to_rna_table)
  end

  def self.of_rna(strand)
    self.complement(strand, rna_to_dna_table)
  end

  def self.complement(strand, complement_table)
    strand.chars.map { |nucleotide| complement_table[nucleotide] }.join
  end

  def self.dna_to_rna_table
    @dna_to_rna_table ||= DNA_RNA_COMPLEMENT
  end

  def self.rna_to_dna_table
    @rna_to_dna_table || dna_to_rna_table.invert
  end


end
