class Nucleotide
  VALID_NUCLEOTIDES = %w[A T C G]

  def self.from_dna(string)
    new(string)
  end

  def initialize(string)
    @nucleotides = build_array(string)
    raise ArgumentError unless is_valid?(@nucleotides)
  end

  def count(nucleotide)
    @nucleotides.select{ |n| n == nucleotide }.count
  end

  def histogram
    Hash[VALID_NUCLEOTIDES.map { |n| [n, count(n)] }]
  end

  private

  def build_array(string)
    string.upcase.split('')
  end

  def is_valid?(nucleotides)
    nucleotides.empty? || contains_only_valid_nucleotides(nucleotides)
  end

  def contains_only_valid_nucleotides(nucleotides)
    (nucleotides.uniq - VALID_NUCLEOTIDES).empty?
  end
end
