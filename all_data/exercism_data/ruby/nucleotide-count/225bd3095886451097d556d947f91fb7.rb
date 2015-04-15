class DNA
  NUCLEOTIDES = %w(A T C G U).freeze

  attr_reader :nucleotide_counts

  def initialize(dna)
    @nucleotide_counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    dna.upcase.chars.each do |nucleotide|
      @nucleotide_counts[nucleotide] += 1
    end
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include? nucleotide
    nucleotide_counts.fetch(nucleotide) { 0 }
  end
end
