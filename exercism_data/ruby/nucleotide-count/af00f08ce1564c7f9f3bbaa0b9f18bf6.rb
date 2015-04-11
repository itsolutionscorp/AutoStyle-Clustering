class Acid
  NUCLEOTIDES = %w[A G C T U]

private
  def validate_nucleotide(nucleotide)
    raise ArgumentError, "#{nucleotide} is not a valid nucleotide" unless Acid::NUCLEOTIDES.include?(nucleotide)
  end

end


class DNA < Acid
  attr_reader :dna_string

  def initialize(dna_string)
    @dna_string = dna_string
  end

  def count(nucleotide)
    validate_nucleotide(nucleotide)
    dna_string.count(nucleotide)
  end

  def nucleotide_counts
    count_hash = {}
    nucleotides.each { |n| count_hash[n] = count(n) }
    count_hash
  end

private

  def nucleotides
    Acid::NUCLEOTIDES - ["U"]
  end

end
