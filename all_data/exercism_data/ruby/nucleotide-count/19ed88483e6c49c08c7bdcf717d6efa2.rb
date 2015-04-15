require 'set'

class DNA
  DNA_NUCLEOTIDES = Set.new ['A', 'T', 'C', 'G' ]
  VALID_NUCLEOTIDES = [*DNA_NUCLEOTIDES, 'U']

  attr_reader :nucleotide_counts

  def initialize(sequence)
    assert_is_dna sequence
    @sequence = sequence
    initialize_counts
    count_nucleotides
  end

  def count(nucleotide)
    assert_is_nucleotide nucleotide
    @nucleotide_counts[nucleotide]
  end

  private

  def count_nucleotides
    @sequence.chars.each do |nucleotide|
      @nucleotide_counts[nucleotide] += 1
    end
  end

  def initialize_counts
    @nucleotide_counts = Hash.new(0)
    DNA_NUCLEOTIDES.each do |nucleotide|
      @nucleotide_counts[nucleotide] = 0
    end
  end

  def assert_is_nucleotide(argument)
    unless VALID_NUCLEOTIDES.include? argument
      raise ArgumentError
    end
  end

  def assert_is_dna(sequence)
    nucleotides = Set.new(sequence.upcase.chars)
    unless DNA_NUCLEOTIDES.superset? nucleotides
      raise ArgumentError
    end
  end
end
