class DNA
  DNA_NUCLEOTIDES = ['A', 'T', 'C', 'G']
  RNA_NUCLEOTIDES = ['A', 'T', 'C', 'U']

  attr_reader :dna_string

  def initialize(dna_string)
    @dna_string = dna_string
  end

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotides.include?(nucleotide)
    dna_string.count(nucleotide)
  end

  def nucleotide_counts
    @nucleotide_counts ||= find_nucleotide_counts
  end

  private

  def find_nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] ||= count(nucleotide)
    end
  end

  def valid_nucleotides
    @all_nucleotides ||= (DNA_NUCLEOTIDES + RNA_NUCLEOTIDES).uniq
  end
end
