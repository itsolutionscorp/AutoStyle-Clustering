class Complement
  attr_reader :sequence, :complements

  DNA_COMPLEMENTS = { 'G' => 'C',
                      'C' => 'G',
                      'T' => 'A',
                      'A' => 'U' }

  def self.of_dna(dna)
    new(sequence: dna, complements: DNA_COMPLEMENTS).perform
  end

  def self.of_rna(rna)
    new(sequence: rna, complements: DNA_COMPLEMENTS.invert).perform
  end

  def initialize(sequence: nil, complements: nil)
    @sequence = sequence.chars
    @complements = complements
  end

  def perform
    sequence.collect { |nucleotide| complements[nucleotide] }.join
  end
end
