class Complement
  def initialize(strand)
    @strand = strand
    @complement = []
  end

  def self.of_dna(strand)
    new(strand).get_complement dna_to_rna
  end

  def self.of_rna(strand)
    new(strand).get_complement rna_to_dna
  end

  def get_complement(map)
    @strand.split('').each { |nuc| @complement << map[nuc] }
    @complement.join()
  end

  def self.dna_to_rna
    { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }
  end

  def self.rna_to_dna
    { 'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T' }
  end
end
