class Complement
  attr_reader :sequence, :complements

  DNA_COMPLEMENTS = { 'G' => 'C',
                      'C' => 'G',
                      'T' => 'A',
                      'A' => 'U' }

  def self.of_dna(dna)
    new(dna: dna).perform
  end

  def self.of_rna(rna)
    new(rna: rna).perform
  end

  def initialize(dna: nil, rna: nil)
    @sequence    = (dna || rna).chars
    @complements = (dna &&= DNA_COMPLEMENTS) ||
                   (rna &&= DNA_COMPLEMENTS.invert)
  end

  def perform
    sequence.collect { |nucleotide| complements[nucleotide] }.join
  end
end
