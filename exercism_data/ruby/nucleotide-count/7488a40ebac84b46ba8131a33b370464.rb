require 'set'

class DNA
  attr_reader :nucleotide_counts

  DNA_NUCLEOTIDES = Set.new(%w[A T C G])
  RNA_NUCLEOTIDES = Set.new(%w[U])
  VALID_NUCLEOTIDES = DNA_NUCLEOTIDES + RNA_NUCLEOTIDES

  def initialize(sequence)
    @nucleotide_counts = educe_nucleotides(sequence.chars)
  end

  def count(nucleotide)
    validate_nucleotide!(nucleotide)
    nucleotide_counts[nucleotide]
  end

  private

  def educe_nucleotides(sequence)
    counts = empty_counts(DNA_NUCLEOTIDES)
    sequence.each { |nucleotide| counts[nucleotide] += 1 }
    counts
  end

  def empty_counts(nucleotides)
    counts = Hash.new(0)
    nucleotides.each_with_object(counts) { |nucleotide, hash| hash[nucleotide] = 0 }
  end

  def validate_nucleotide!(nucleotide)
    unless VALID_NUCLEOTIDES.include?(nucleotide)
      raise ArgumentError, "invalid nucleotide #{nucleotide.inspect}"
    end
  end
end
