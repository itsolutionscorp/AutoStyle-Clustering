class Nucleotide
  attr_reader :letters

  NUCLEOTIDES = %w(A T C G)
  INVALID_NUCLEOTIDES = /[^ATCG]/

  def self.from_dna(dna)
    fail ArgumentError, "invalid nucleotide(s) in DNA string" if dna =~ INVALID_NUCLEOTIDES
    new(dna.chars)
  end

  def initialize(letters)
    @letters = letters
  end

  def count(letter)
    letters.count(letter)
  end

  def histogram
    NUCLEOTIDES.each_with_object(Hash.new) do |nucleotide, histogram|
      histogram[nucleotide] = count(nucleotide)
    end
  end
end
