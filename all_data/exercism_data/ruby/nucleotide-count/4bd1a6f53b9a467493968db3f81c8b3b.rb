class DNA
  DNA_NUCLEOTIDES = ['A', 'T', 'C', 'G']
  RNA_NUCLEOTIDES = ['A', 'T', 'C', 'U']

  attr_accessor :counts
  attr_reader :dna_sequence

  def initialize(dna_sequence)
    @dna_sequence = dna_sequence.split('')
  end

  def count(nucleotide)
    raise ArgumentError unless all_nucleotides.include?(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    counts ||= find_nucleotide_counts
  end

  private

  def find_nucleotide_counts
    dna_sequence.each_with_object(empty_nucleotide_counts) do |nucleotide, counts|
      counts[nucleotide] += 1
    end
  end

  def empty_nucleotide_counts
    @empty_nucleotide_counts ||= 
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |nucleotide, empty_hash|
      empty_hash[nucleotide] = 0
    end
  end

  def all_nucleotides
    @all_nucleotides ||= (DNA_NUCLEOTIDES + RNA_NUCLEOTIDES).uniq
  end
end
