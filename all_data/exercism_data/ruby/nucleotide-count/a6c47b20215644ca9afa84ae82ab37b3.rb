require 'set'

class DNA
  DNA_NUCLEOTIDES = Set.new ['A', 'T', 'C', 'G' ]
  VALID_NUCLEOTIDES = [*DNA_NUCLEOTIDES, 'U']

  attr_reader :nucleotide_counts

  def initialize(sequence)
    assert_is_dna sequence
    @counter = DnaCounter.new(sequence)
    @nucleotide_counts = @counter.nucleotide_counts
  end

  def count(nucleotide)
    assert_is_nucleotide nucleotide
    nucleotide_counts[nucleotide]
  end

  private

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

class DnaCounter
  attr_reader :nucleotide_counts

  def initialize(sequence)
    @sequence = sequence
    @nucleotide_counts = count_each_nucleotide
  end

  private
  attr_reader :sequence

  def initialize_counts
    DNA::DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do
      |nucleotide, empty_counts|

      empty_counts[nucleotide] = 0
    end
  end

  def count_each_nucleotide
    sequence.chars.each_with_object(initialize_counts) do
      |nucleotide, counts|

      counts[nucleotide] += 1
    end
  end
end
