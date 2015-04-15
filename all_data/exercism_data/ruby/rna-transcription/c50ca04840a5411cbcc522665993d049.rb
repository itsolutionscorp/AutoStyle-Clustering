RNA = Struct.new(:strand) do
  TRANSCRIBE_2_DNA = { 'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T' }

  def to_dna
    DNA.new(strand.chars.map { |n| TRANSCRIBE_2_DNA[n] }.join)
  end
end

DNA = Struct.new(:strand) do
  TRANSCRIBE_2_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def to_rna
    RNA.new(strand.chars.map { |n| TRANSCRIBE_2_RNA[n] }.join)
  end
end

module Complement
  def self.of_dna(strand)
    DNA.new(strand).to_rna.strand
  end

  def self.of_rna(strand)
    RNA.new(strand).to_dna.strand
  end
end
