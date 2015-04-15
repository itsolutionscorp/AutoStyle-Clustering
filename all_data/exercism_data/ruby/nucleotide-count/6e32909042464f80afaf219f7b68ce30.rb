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
    @initial_counts = initialize_counts
    @nucleotide_counts = count_each_nucleotide(initial_counts)
  end

  private
  attr_reader :sequence, :initial_counts

  def initialize_counts
    empty_counts = Hash.new(0)
    DNA::DNA_NUCLEOTIDES.each do |nucleotide|
      empty_counts[nucleotide] = 0
    end
    empty_counts
  end

  def count_each_nucleotide(counter)
    sequence.chars.each do |nucleotide|
      counter[nucleotide] += 1
    end
    counter
  end
end
