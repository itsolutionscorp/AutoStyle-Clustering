class DNA
  DNA_NUCLEOTIDES = ['A', 'T', 'C', 'G']
  RNA_NUCLEOTIDES = ['A', 'T', 'C', 'U']

  attr_reader :dna_sequence

  def initialize(dna_sequence)
    @dna_sequence = dna_sequence
  end

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotides.include?(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    @nucleotide_counts ||= find_nucleotide_counts
  end

  private

  def find_nucleotide_counts
    each_nucleotide_with_counts(empty_nucleotide_counts) do |nucleotide, counts|
      counts[nucleotide] += 1
    end
  end

  def each_nucleotide_with_counts(counts)
    dna_sequence.split('').each_with_object(counts) do |nucleotide, counts|
      yield nucleotide, counts
    end
  end

  def empty_nucleotide_counts
    @empty_nucleotide_counts ||= 
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |nucleotide, empty_hash|
      empty_hash[nucleotide] = 0
    end
  end

  def valid_nucleotides
    @all_nucleotides ||= (DNA_NUCLEOTIDES + RNA_NUCLEOTIDES).uniq
  end
end
