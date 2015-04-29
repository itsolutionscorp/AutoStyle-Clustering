class Acid
  NUCLEOTIDES = %w[A G C T U]

private
  def validate_nucleotide(nucleotide)
    raise ArgumentError, "#{nucleotide} is not a valid nucleotide" unless NUCLEOTIDES.include?(nucleotide)
  end

end


class DNA < Acid
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def count(nucleotide)
    validate_nucleotide(nucleotide)
    strand.count(nucleotide)
  end

  def nucleotide_counts
    nucleotides.inject({}) do |collection, value|
      collection[value] = count(value)
      collection
    end
  end

private

  def nucleotides
    Acid::NUCLEOTIDES - ["U"]
  end

end
