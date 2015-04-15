module Complement
  DNA_NUCLEOTIDES = %w( G C T A )
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_NUCLEOTIDES = %w( C G A U )
  RNA_TO_DNA = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }

  def self.of_dna(dna)
    raise ArgumentError if dna.chars.any? {|c| !DNA_NUCLEOTIDES.include?(c) }
    dna.chars.map {|c| DNA_TO_RNA[c] }.join
  end

  def self.of_rna(rna)
    raise ArgumentError if rna.chars.any? {|c| !RNA_NUCLEOTIDES.include?(c) }
    rna.chars.map {|c| RNA_TO_DNA[c] }.join
  end
end
