# nucleotide.rb
class Nucleotide
  VALID_NUCLEOTIDES = %w(A C G T)

  def self.from_dna(dna)
    fail ArgumentError if dna =~ /[^#{VALID_NUCLEOTIDES.join}]/
    Nucleotide.new(dna)
  end

  attr_reader :dna

  def initialize(dna)
    @dna = dna
  end

  def count(nucleotide)
    dna.count(nucleotide)
  end

  def histogram
    VALID_NUCLEOTIDES.each_with_object({}) do |nucleotide, hash|
      hash[nucleotide] = count(nucleotide)
    end
  end
end
