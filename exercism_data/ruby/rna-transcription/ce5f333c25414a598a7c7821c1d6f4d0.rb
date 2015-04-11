class Complement
  NUCLEOTIDES_MAP = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(dna_strand)
    complement_strand(dna_strand, NUCLEOTIDES_MAP)
  end

  def self.of_rna(rna_strand)
    complement_strand(rna_strand, NUCLEOTIDES_MAP.invert)
  end

  private

  def self.complement_strand(strand, nucleotides_map)
    complement = ''

    strand.each_char do |char|
      complement << nucleotides_map[char]
    end

    complement
  end
end
