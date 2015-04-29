class Complement
  def self.of_dna strand
    find_complement strand, self.rna_complement_lookup
  end

  def self.of_rna strand
    find_complement strand, self.dna_complement_lookup
  end

  private

  def self.find_complement strand, lookup
    strand.chars.map{ |nucleotide| lookup[nucleotide] }.join
  end

  def self.rna_complement_lookup
    DNA_TO_RNA_TABLE.invert
  end

  def self.dna_complement_lookup
    DNA_TO_RNA_TABLE
  end

  DNA_TO_RNA_TABLE = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }
end
